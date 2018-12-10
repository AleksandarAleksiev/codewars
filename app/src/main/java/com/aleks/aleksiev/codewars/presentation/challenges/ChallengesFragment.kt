package com.aleks.aleksiev.codewars.presentation.challenges

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.databinding.FragmentChallengesBinding
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeModel
import com.aleks.aleksiev.codewars.presentation.common.BaseFragment
import com.aleks.aleksiev.codewars.utils.BundleDelegate
import com.aleks.aleksiev.codewars.utils.NetworkState
import com.aleks.aleksiev.codewars.utils.RecyclerViewItemsSpaceDecoration
import javax.inject.Inject

class ChallengesFragment : BaseFragment(), UserIdProvider {

    private var memberId: Long = 0

    @Inject
    lateinit var challengesAdapter: ChallengesAdapter

    val challengesViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ChallengesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = savedInstanceState ?: arguments
        bundle?.let {
            this.memberId = it.memberId
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentChallengesBinding>(inflater, R.layout.fragment_challenges, container, false)
        binding.challengesViewModel = challengesViewModel
        initView(binding)
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        challengesViewModel.dispose()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.memberId = this.memberId
    }

    override fun getUserId(): Long = memberId

    private fun initView(binding: FragmentChallengesBinding) {
        val topBottomSpace = resources.getDimensionPixelSize(R.dimen.top_margin)
        val startEndSpace = resources.getDimensionPixelSize(R.dimen.top_margin)
        val linearLayoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.challengesRecyclerView.layoutManager = linearLayoutManager
        binding.challengesRecyclerView.adapter = challengesAdapter
        binding.challengesRecyclerView.addItemDecoration(DividerItemDecoration(this.context, linearLayoutManager.orientation))
        binding.challengesRecyclerView.addItemDecoration(RecyclerViewItemsSpaceDecoration(startEndSpace, topBottomSpace, startEndSpace, topBottomSpace))

        challengesViewModel.challenges.observe(viewLifecycleOwner, Observer<PagedList<ChallengeModel>> { challengesAdapter.submitList(it) })
        challengesViewModel.getNetworkState().observe(viewLifecycleOwner, Observer<NetworkState> { challengesAdapter.setNetworkState(it) })
    }

    companion object {
        @JvmStatic
        val TAG: String = ChallengesFragment::class.java.simpleName

        private var Bundle.memberId by BundleDelegate.BundleLong("$TAG.memberChallenges.id")

        @JvmStatic
        fun newInstance(memberId: Long) = ChallengesFragment().apply {
            arguments = Bundle().apply {
                this.memberId = memberId
            }
        }
    }
}
