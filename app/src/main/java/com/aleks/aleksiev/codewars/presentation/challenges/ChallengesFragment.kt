package com.aleks.aleksiev.codewars.presentation.challenges

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.databinding.FragmentMemberBinding
import com.aleks.aleksiev.codewars.presentation.common.BaseFragment
import com.aleks.aleksiev.codewars.utils.BundleDelegate

class ChallengesFragment : BaseFragment() {

    private var memberId: Long = 0

    private val challengesViewModel by lazy {
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
        val loginBinding = DataBindingUtil.inflate<FragmentMemberBinding>(inflater, R.layout.fragment_member, container, false)
        loginBinding.challengesViewModel = challengesViewModel
        return loginBinding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.memberId = this.memberId
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
