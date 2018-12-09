package com.aleks.aleksiev.codewars.domain.datamodel

import java.util.Date

data class CompletedChallenge(
    val challengeId: Long,
    val challengeName: String,
    val challengeCompletedAt: Date
)