package com.aleks.aleksiev.codewars.presentation.search


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.databinding.FragmentSearchBinding
import com.aleks.aleksiev.codewars.presentation.common.BaseFragment
import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMember
import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMembersAdapter
import com.aleks.aleksiev.codewars.utils.ItemClicked
import com.aleks.aleksiev.codewars.utils.RecyclerViewItemsSpaceDecoration
import javax.inject.Inject

class SearchFragment : BaseFragment(),
    SearchView.OnQueryTextListener,
    SearchHistoryUpdateListener,
    ItemClicked<FoundMember> {

    @Inject
    lateinit var foundMembersAdapter: FoundMembersAdapter

    private val searchViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[SearchViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val searchBinding = DataBindingUtil.inflate<FragmentSearchBinding>(inflater, R.layout.fragment_search, container, false)
        searchBinding.searchViewModel = searchViewModel
        initView(searchBinding)

        return searchBinding.root
    }

    override fun onResume() {
        super.onResume()
        viewLifecycleOwner
        searchViewModel.searchHistory()
    }

    override fun onPause() {
        super.onPause()
        searchViewModel.dispose()
    }

    override fun onQueryTextSubmit(queryText: String?): Boolean {
        searchViewModel.findMember(queryText)
        return true
    }

    override fun onQueryTextChange(queryText: String?): Boolean {
        return false
    }

    override fun searchHistoryUpdated(searchHistory: List<FoundMember>) {
        foundMembersAdapter.submitList(searchHistory)
    }

    override fun onItemClicked(item: FoundMember) {
        searchViewModel.memberChallenges(item.memberId)
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
    }

    companion object {
        @JvmStatic
        val TAG: String = SearchFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}
