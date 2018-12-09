package com.aleks.aleksiev.codewars.domain.rest.response

import com.aleks.aleksiev.codewars.domain.rest.model.Challenge
import com.google.gson.annotations.SerializedName

data class CompletedChallengesResponse(
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("data")
    val challenges: List<Challenge>
)