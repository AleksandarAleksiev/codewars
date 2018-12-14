package com.aleks.aleksiev.codewars.domain.rest.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Challenge(
    @SerializedName("id")
    val challengeId: String,
    @SerializedName("name")
    val challengeName: String?,
    @SerializedName("slug")
    val challengeSlug: String?,
    @SerializedName("completedAt")
    val completedAt: Date,
    @SerializedName("completedLanguages")
    val completedLanguages: List<String>
)