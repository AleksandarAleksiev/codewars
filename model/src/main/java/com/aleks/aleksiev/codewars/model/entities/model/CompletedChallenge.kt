package com.aleks.aleksiev.codewars.model.entities.model

import java.util.Date

data class CompletedChallenge(
    val challengeId: String,
    val challengeName: String,
    val challengeSlug: String,
    val completedAt: Date,
    val completedLanguages: List<String>
)