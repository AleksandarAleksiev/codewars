package com.aleks.aleksiev.codewars.domain.rest.model

import com.google.gson.annotations.SerializedName

data class Rank(
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("name")
    val rankName: String,
    @SerializedName("color")
    val rankColour: String,
    @SerializedName("score")
    val rankScore: Int
)