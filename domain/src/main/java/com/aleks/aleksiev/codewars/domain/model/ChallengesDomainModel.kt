package com.aleks.aleksiev.codewars.domain.model

data class ChallengesDomainModel(
    val totalPages: Int,
    val currentPage: Int,
    val challenges: List<ChallengeDomainModel>
)