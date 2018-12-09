package com.aleks.aleksiev.codewars.domain.rest

import com.aleks.aleksiev.codewars.domain.rest.response.ApiResponse
import com.aleks.aleksiev.codewars.domain.rest.response.MemberSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserController {

    @GET("users/{userId}")
    fun findUser(@Path("userId") userId: String): ApiResponse<MemberSearchResponse>
}