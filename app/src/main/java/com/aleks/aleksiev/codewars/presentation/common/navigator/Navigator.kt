package com.aleks.aleksiev.codewars.presentation.common.navigator

import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeType

interface Navigator {

    fun search()
    fun memberChallenges(memberId: Long)
    fun taskInProgress(inProgress: Boolean)
    fun challengeDetails(challengesGroupId: Long, challengeId: String, challengeType: ChallengeType)
}