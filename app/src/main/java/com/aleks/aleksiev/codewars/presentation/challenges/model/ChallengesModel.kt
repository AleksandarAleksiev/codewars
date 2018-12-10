package com.aleks.aleksiev.codewars.presentation.challenges.model

data class ChallengesModel(
    val page: Int,
    val totalPages: Int,
    val challenges: List<ChallengeModel>
)