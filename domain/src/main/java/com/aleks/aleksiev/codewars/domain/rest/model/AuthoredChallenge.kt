package com.aleks.aleksiev.codewars.domain.rest.model

import com.google.gson.annotations.SerializedName

data class AuthoredChallenge(
    @SerializedName("id")
    val challengeId: String,
    @SerializedName("name")
    val challengeName: String,
    @SerializedName("description")
    val challengeDescription: String,
    @SerializedName("rank")
    val challengeRank: Int,
    @SerializedName("rankName")
    val challengeRankName: String?,
    @SerializedName("tags")
    val challengeTags: List<String>,
    @SerializedName("languages")
    val challengeLanguages: List<String>
)