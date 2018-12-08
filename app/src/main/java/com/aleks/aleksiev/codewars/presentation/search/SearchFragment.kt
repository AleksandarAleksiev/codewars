package com.aleks.aleksiev.codewars.presentation.search


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.databinding.FragmentSearchBinding
import com.aleks.aleksiev.codewars.presentation.common.BaseFragment

class SearchFragment : BaseFragment() {
    private val searchViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[SearchViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val homeBinding = DataBindingUtil.inflate<FragmentSearchBinding>(inflater, R.layout.fragment_search, container, false)
        homeBinding.searchViewModel = searchViewModel
        return homeBinding.root
    }

    companion object {
        @JvmStatic
        val TAG: String = SearchFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}
