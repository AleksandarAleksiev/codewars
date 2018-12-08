package com.aleks.aleksiev.codewars.presentation.member

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.databinding.FragmentMemberBinding
import com.aleks.aleksiev.codewars.presentation.common.BaseFragment

class MemberFragment : BaseFragment() {

    private val memberViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[MemberViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val loginBinding = DataBindingUtil.inflate<FragmentMemberBinding>(inflater, R.layout.fragment_member, container, false)
        loginBinding.memberViewModel = memberViewModel
        return loginBinding.root
    }

    companion object {
        @JvmStatic
        val TAG: String = MemberFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = MemberFragment()
    }
}
