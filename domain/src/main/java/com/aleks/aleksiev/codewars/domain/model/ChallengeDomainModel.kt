package com.aleks.aleksiev.codewars.domain.model

data class ChallengeDomainModel(
    val challengesGroupId: Long,
    val challengeId: String,
    val challengeName: String,
    val completedAt: String,
    val challengeDescription: String
)