package com.aleks.aleksiev.codewars.presentation.common.navigator

import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeType

interface Navigation {

    fun search()
    fun memberChallenges(memberId: Long)
    fun taskInProgress(inProgress: Boolean)
    fun challengeDetails(challengesGroupId: Long, challengeId: String, challengeType: ChallengeType)
    fun showSnackBar(snackBarText: String, @ColorRes backgroundColour: Int, @ColorRes foregroundColour: Int)
    fun showSnackBar(@StringRes messageId: Int, @ColorRes backgroundColour: Int, @ColorRes foregroundColour: Int)
}