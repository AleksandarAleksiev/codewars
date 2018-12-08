package com.aleks.aleksiev.codewars.presentation.common.navigator

import android.support.v4.app.FragmentManager
import com.aleks.aleksiev.codewars.presentation.search.SearchFragment
import com.aleks.aleksiev.codewars.presentation.member.MemberFragment
import com.aleks.aleksiev.codewars.presentation.main.MainActivity
import javax.inject.Inject

class AppNavigation @Inject constructor(private val mainActivity: MainActivity) : Navigator {
    override fun search() {
        var fragment = mainActivity.supportFragmentManager.findFragmentByTag(SearchFragment.TAG) as? SearchFragment
        if (fragment == null) {
            fragment = SearchFragment.newInstance()
            mainActivity.showScreen(fragment, SearchFragment.TAG, false, true)
        } else {
            clearBackStack()
        }
    }

    override fun member() {
        val fragment = mainActivity.supportFragmentManager.findFragmentByTag(MemberFragment.TAG) as? MemberFragment
            ?: MemberFragment.newInstance()
        mainActivity.showScreen(fragment, MemberFragment.TAG, false, false)
    }

    private fun clearBackStack() {

        // this will clear the back stack and displays no animation on the screen
        mainActivity.supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}