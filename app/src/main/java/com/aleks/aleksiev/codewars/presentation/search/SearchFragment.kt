package com.aleks.aleksiev.codewars.presentation.search


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.databinding.FragmentSearchBinding
import com.aleks.aleksiev.codewars.domain.model.SortBy
import com.aleks.aleksiev.codewars.presentation.RenderState
import com.aleks.aleksiev.codewars.presentation.common.BaseFragment
import com.aleks.aleksiev.codewars.presentation.hideKeyboard
import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMember
import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMembersAdapter
import com.aleks.aleksiev.codewars.utils.BundleDelegate
import com.aleks.aleksiev.codewars.utils.Constants
import com.aleks.aleksiev.codewars.utils.ItemClicked
import com.aleks.aleksiev.codewars.utils.RecyclerViewItemsSpaceDecoration
import javax.inject.Inject

class SearchFragment : BaseFragment(),
    SearchView.OnQueryTextListener,
    SearchHistoryUpdateListener,
    ItemClicked<FoundMember>, AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var foundMembersAdapter: FoundMembersAdapter

    private val searchViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[SearchViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            searchViewModel.sortBy = SortBy.fromInt(it.sortBy)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val searchBinding = DataBindingUtil.inflate<FragmentSearchBinding>(inflater, R.layout.fragment_search, container, false)
        searchBinding.searchViewModel = searchViewModel
        initView(searchBinding)

        searchViewModel.renderState.observe(viewLifecycleOwner, Observer<RenderState> { renderState(it) })

        return searchBinding.root
    }

    override fun onResume() {
        super.onResume()
        searchViewModel.searchHistory()
    }

    override fun onPause() {
        super.onPause()
        searchViewModel.dispose()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.sortBy = searchViewModel.sortBy.ordinal
    }

    override fun onQueryTextSubmit(queryText: String?): Boolean {
        parentActivity?.hideKeyboard()
        queryText?.let {
            searchViewModel.findMember(it)
        }
        return true
    }

    override fun onQueryTextChange(queryText: String?): Boolean {
        return false
    }

    override fun searchHistoryUpdated(searchHistory: List<FoundMember>) {
        foundMembersAdapter.submitList(searchHistory)
    }

    override fun onItemClicked(item: FoundMember) {
        this.navigator?.memberChallenges(item.memberId)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.selectedItem?.toString()?.let {
            if (it.equals(Constants.SORT_BY_DATE, true)) {
                searchViewModel.sortBy = SortBy.Date
            } else {
                searchViewModel.sortBy = SortBy.Rank
            }
        }
    }

    private fun initView(searchBinding: FragmentSearchBinding) {
        searchBinding.searchView.isIconified = false
        searchBinding.searchView.isSubmitButtonEnabled = true
        searchBinding.searchView.isActivated = true
        searchBinding.searchView.clearFocus()
        searchBinding.searchView.setOnQueryTextListener(this)

        val topBottomSpace = resources.getDimensionPixelSize(R.dimen.top_margin)
        val startEndSpace = resources.getDimensionPixelSize(R.dimen.top_margin)
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        searchBinding.searchHistoryRecyclerView.layoutManager = layoutManager
        searchBinding.searchHistoryRecyclerView.adapter = foundMembersAdapter
        searchBinding.searchHistoryRecyclerView.addItemDecoration(DividerItemDecoration(this.context, layoutManager.orientation))
        searchBinding.searchHistoryRecyclerView.addItemDecoration(RecyclerViewItemsSpaceDecoration(startEndSpace, topBottomSpace, startEndSpace, topBottomSpace))

        searchBinding.sortBySpinner.onItemSelectedListener = this

        ArrayAdapter.createFromResource(
            this.requireContext().applicationContext,
            R.array.sort_by_array,
            R.layout.layout_sort_by
        ).also { adapter ->
            //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            searchBinding.sortBySpinner.adapter = adapter
        }
    }

    companion object {
        @JvmStatic
        val TAG: String = SearchFragment::class.java.simpleName

        var Bundle.sortBy by BundleDelegate.BundleInt("$TAG.sort.by", SortBy.Date.ordinal)

        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}
