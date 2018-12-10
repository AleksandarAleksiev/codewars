package com.aleks.aleksiev.codewars.domain.rest.response

import com.aleks.aleksiev.codewars.domain.rest.model.AuthoredChallenge
import com.google.gson.annotations.SerializedName

data class AuthoredChallengesResponse(
    @SerializedName("data")
    val challenges: List<AuthoredChallenge>
)