package com.aleks.aleksiev.codewars.presentation.challengedetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.databinding.FragmentChallengeDetailsBinding
import com.aleks.aleksiev.codewars.presentation.RenderState
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeType
import com.aleks.aleksiev.codewars.presentation.common.BaseFragment
import com.aleks.aleksiev.codewars.utils.BundleDelegate

class ChallengeDetailsFragment : BaseFragment() {

    private val challengeDetailsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ChallengeDetailsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = savedInstanceState ?: arguments
        bundle?.let {
            challengeDetailsViewModel.challengeDetailsState.challengeId = it.challengeId
            challengeDetailsViewModel.challengeDetailsState.challengesId = it.challengesId
            challengeDetailsViewModel.challengeDetailsState.challengeType = ChallengeType.fromInt(it.challengesType)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentChallengeDetailsBinding>(inflater, R.layout.fragment_challenge_details, container, false)
        binding.challenge = challengeDetailsViewModel.challengeDetailsState
        challengeDetailsViewModel.renderState.observe(viewLifecycleOwner, Observer<RenderState> { renderState(it) })
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        challengeDetailsViewModel.getChallengeDetails()
    }

    override fun onPause() {
        super.onPause()
        challengeDetailsViewModel.dispose()
    }

    companion object {

        @JvmStatic
        val TAG: String = ChallengeDetailsFragment::class.java.simpleName

        private var Bundle.challengeId by BundleDelegate.BundleString("$TAG.challenges.challenge.id")
        private var Bundle.challengesId by BundleDelegate.BundleLong("$TAG.challenges.id")
        private var Bundle.challengesType by BundleDelegate.BundleInt("$TAG.challenges.type")

        @JvmStatic
        fun newInstance(challengesId: Long, challengeId: String, challengesType: ChallengeType): ChallengeDetailsFragment {
            return ChallengeDetailsFragment().apply {
                this.arguments = Bundle().apply {
                    this.challengeId = challengeId
                    this.challengesId = challengesId
                    this.challengesType = challengesType.ordinal
                }
            }
        }
    }
}
