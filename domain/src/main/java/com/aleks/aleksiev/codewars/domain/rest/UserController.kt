package com.aleks.aleksiev.codewars.domain.rest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserController {

    @GET("users/{userId}")
    fun findUser(@Path("userId") userId: String): Call<UserResponse>
}