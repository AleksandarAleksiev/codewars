package com.aleks.aleksiev.codewars.presentation.challenges

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.databinding.FragmentChallengesBinding
import com.aleks.aleksiev.codewars.presentation.RenderState
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeModel
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeType
import com.aleks.aleksiev.codewars.presentation.common.BaseFragment
import com.aleks.aleksiev.codewars.utils.BundleDelegate
import com.aleks.aleksiev.codewars.utils.Event
import com.aleks.aleksiev.codewars.utils.ItemClicked
import com.aleks.aleksiev.codewars.utils.NetworkState
import com.aleks.aleksiev.codewars.utils.RecyclerViewItemsSpaceDecoration
import javax.inject.Inject

class ChallengesFragment : BaseFragment(), ItemClicked<ChallengeModel?> {

    @Inject
    lateinit var challengesAdapter: ChallengesAdapter

    val challengesViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ChallengesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = savedInstanceState ?: arguments
        bundle?.let {
            this.challengesViewModel.userId = it.memberId
            this.challengesViewModel.selectedItem = it.selectedMenuId
        }

        challengesAdapter.itemClickedHandler = this
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
        challengesViewModel.dispose()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.memberId = this.challengesViewModel.userId
        outState.selectedMenuId = this.challengesViewModel.selectedItem
    }

    override fun onItemClicked(item: ChallengeModel?) {
        item?.let {
            navigator?.challengeDetails(it.challengesGroupId, it.challengeId, challengesViewModel.challengeType)
        }
    }

    private fun initView(binding: FragmentChallengesBinding) {
        val topBottomSpace = resources.getDimensionPixelSize(R.dimen.top_margin)
        val startEndSpace = resources.getDimensionPixelSize(R.dimen.top_margin)
        val linearLayoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.challengesRecyclerView.layoutManager = linearLayoutManager
        binding.challengesRecyclerView.adapter = challengesAdapter
        binding.challengesRecyclerView.addItemDecoration(DividerItemDecoration(this.context, linearLayoutManager.orientation))
        binding.challengesRecyclerView.addItemDecoration(RecyclerViewItemsSpaceDecoration(startEndSpace, topBottomSpace, startEndSpace, topBottomSpace))

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            challengesViewModel.invalidate(it.itemId)
            true
        }

        selectChallenge(binding.bottomNavigationView)

        challengesViewModel.challenges.observe(viewLifecycleOwner, Observer<PagedList<ChallengeModel>> { challengesAdapter.submitList(it) })
        challengesViewModel.getNetworkState().observe(viewLifecycleOwner, Observer<NetworkState> { challengesAdapter.setNetworkState(it) })
        challengesViewModel.renderState.observe(viewLifecycleOwner, Observer<Event<RenderState>> {
            it?.getContentIfNotHandled()?.let { state ->
                renderState(state)
            }
        })
    }

    private fun selectChallenge(bottomNavigationView: BottomNavigationView) {
        when (this.challengesViewModel.challengeType) {
            ChallengeType.Authored -> bottomNavigationView.selectedItemId = R.id.action_authored_challenges
            else -> bottomNavigationView.selectedItemId = R.id.action_completed_challenges
        }
    }

    companion object {
        @JvmStatic
        val TAG: String = ChallengesFragment::class.java.simpleName

        private var Bundle.memberId by BundleDelegate.BundleLong("$TAG.memberChallenges.id")
        private var Bundle.selectedMenuId by BundleDelegate.BundleInt("$TAG.memberChallenges.selected.item.id")

        @JvmStatic
        fun newInstance(memberId: Long) = ChallengesFragment().apply {
            arguments = Bundle().apply {
                this.memberId = memberId
            }
        }
    }
}
