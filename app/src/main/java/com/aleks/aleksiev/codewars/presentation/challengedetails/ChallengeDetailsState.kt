package com.aleks.aleksiev.codewars.presentation.challengedetails

import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeType
import com.aleks.aleksiev.codewars.utils.Constants
import javax.inject.Inject

class ChallengeDetailsState @Inject constructor() {

    var challengeId: String = Constants.EMPTY_STRING
    var challengesId: Long = 0L
    var challengeType: ChallengeType = ChallengeType.Unknown

    var challengeName: String = Constants.EMPTY_STRING
    var challengeCompletedAt: String = Constants.EMPTY_STRING
    var challengeLanguages: String = Constants.EMPTY_STRING
    var challengeTags: String = Constants.EMPTY_STRING
    var challengeDescription: String = Constants.EMPTY_STRING
}