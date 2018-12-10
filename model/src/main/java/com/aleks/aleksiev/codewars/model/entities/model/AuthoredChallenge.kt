package com.aleks.aleksiev.codewars.model.entities.model

data class AuthoredChallenge(
    val challengeId: String,
    val challengeName: String,
    val challengeDescription: String,
    val challengeRank: Int,
    val challengeRankName: String?,
    val tags: List<String>,
    val languages: List<String>
)