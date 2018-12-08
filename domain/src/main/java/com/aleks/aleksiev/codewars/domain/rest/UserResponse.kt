package com.aleks.aleksiev.codewars.domain.rest

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("username")
    val userName: String
)