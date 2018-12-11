package com.aleks.aleksiev.codewars.presentation.common.navigator

import android.support.v4.app.FragmentManager
import com.aleks.aleksiev.codewars.presentation.challengedetails.ChallengeDetailsFragment
import com.aleks.aleksiev.codewars.presentation.challenges.ChallengesFragment
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeType
import com.aleks.aleksiev.codewars.presentation.main.MainActivity
import com.aleks.aleksiev.codewars.presentation.search.SearchFragment

class AppNavigation : Navigator {

    var mainActivity: MainActivity? = null

    override fun search() {
        var fragment = mainActivity?.supportFragmentManager?.findFragmentByTag(SearchFragment.TAG) as? SearchFragment
        if (fragment == null) {
            fragment = SearchFragment.newInstance()
            mainActivity?.showScreen(fragment, SearchFragment.TAG, false, false)
        } else {
            clearBackStack()
        }
    }

    override fun memberChallenges(memberId: Long) {
        val fragment = mainActivity?.supportFragmentManager?.findFragmentByTag(ChallengesFragment.TAG) as? ChallengesFragment
            ?: ChallengesFragment.newInstance(memberId)
        mainActivity?.showScreen(fragment, ChallengesFragment.TAG, true, true)
    }

    override fun challengeDetails(challengesGroupId: Long, challengeId: String, challengeType: ChallengeType) {
        val fragment = mainActivity?.supportFragmentManager?.findFragmentByTag(ChallengeDetailsFragment.TAG) as? ChallengeDetailsFragment
            ?: ChallengeDetailsFragment.newInstance(challengesGroupId, challengeId, challengeType)
        mainActivity?.showScreen(fragment, ChallengeDetailsFragment.TAG, true, true)
    }

    override fun taskInProgress(inProgress: Boolean) {
        mainActivity?.taskInProgress(inProgress)
    }

    private fun clearBackStack() {

        // this will clear the back stack and displays no animation on the screen
        mainActivity?.supportFragmentManager?.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}