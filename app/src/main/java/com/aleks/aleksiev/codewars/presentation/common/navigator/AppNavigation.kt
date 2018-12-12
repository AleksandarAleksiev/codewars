package com.aleks.aleksiev.codewars.presentation.common.navigator

import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.aleks.aleksiev.codewars.presentation.challengedetails.ChallengeDetailsFragment
import com.aleks.aleksiev.codewars.presentation.challenges.ChallengesFragment
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeType
import com.aleks.aleksiev.codewars.presentation.main.MainActivity
import com.aleks.aleksiev.codewars.presentation.search.SearchFragment

class AppNavigation : Navigation {

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

    override fun showSnackBar(
        @StringRes messageId: Int,
        @ColorRes backgroundColour: Int,
        @ColorRes foregroundColour: Int
    ) {

        mainActivity?.let { activity ->
            val snackBarText = activity.getString(messageId)
            showSnackBar(snackBarText, backgroundColour, foregroundColour)
        }
    }

    override fun showSnackBar(
        snackBarText: String,
        @ColorRes backgroundColour: Int,
        @ColorRes foregroundColour: Int
    ) {

        mainActivity?.let { activity ->
            val foreground = ContextCompat.getColor(activity, foregroundColour)
            val background = ContextCompat.getColor(activity, backgroundColour)
            val spannableString = SpannableStringBuilder().append(snackBarText)
            spannableString.setSpan(ForegroundColorSpan(foreground), 0,
                snackBarText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            activity.rootView?.let {
                val snackBar = Snackbar.make(it, snackBarText, Snackbar.LENGTH_LONG)
                snackBar.view.setBackgroundColor(background)
                snackBar.show()
            }
        }
    }

    private fun clearBackStack() {

        // this will clear the back stack and displays no animation on the screen
        mainActivity?.supportFragmentManager?.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}