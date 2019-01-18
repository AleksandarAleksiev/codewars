package com.aleks.aleksiev.codewars.presentation.search


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
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
import com.aleks.aleksiev.codewars.utils.Event
import com.aleks.aleksiev.codewars.utils.ItemClicked
import javax.inject.Inject

class SearchFragment : BaseFragment(),
    SearchView.OnQueryTextListener,
    ItemClicked<FoundMember>, AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var foundMembersAdapter: FoundMembersAdapter

    lateinit var searchBinding: FragmentSearchBinding

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
        searchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        searchBinding.searchViewModel = searchViewModel
        initView()

        searchViewModel.renderState.observe(viewLifecycleOwner, Observer<Event<RenderState>> {
            it?.getContentIfNotHandled()?.let { state ->
                renderState(state)
            }
        })

        searchViewModel.members.observe(viewLifecycleOwner, Observer {
            it?.let { members ->
                foundMembersAdapter.submitList(members)
            }
        })

        return searchBinding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.sortBy = searchViewModel.sortBy.ordinal
    }

    override fun onQueryTextSubmit(queryText: String?): Boolean {
        parentActivity?.hideKeyboard()
        searchBinding.searchView.clearFocus()
        queryText?.let {
            searchViewModel.findMember(it)
        }
        return true
    }

    override fun onQueryTextChange(queryText: String?): Boolean {
        return false
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

    private fun initView() {
        searchBinding.searchView.isIconified = false
        searchBinding.searchView.isSubmitButtonEnabled = true
        searchBinding.searchView.isActivated = true
        searchBinding.searchView.clearFocus()
        searchBinding.searchView.setOnQueryTextListener(this)

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        searchBinding.searchHistoryRecyclerView.layoutManager = layoutManager
        searchBinding.searchHistoryRecyclerView.adapter = foundMembersAdapter

        searchBinding.sortBySpinner.onItemSelectedListener = this

        ArrayAdapter.createFromResource(
            this.requireContext().applicationContext,
            R.array.sort_by_array,
            R.layout.layout_sort_by
        ).also { adapter ->
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
