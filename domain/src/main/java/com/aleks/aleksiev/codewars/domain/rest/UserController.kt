package com.aleks.aleksiev.codewars.domain.rest

import com.aleks.aleksiev.codewars.domain.rest.response.ApiResponse
import com.aleks.aleksiev.codewars.domain.rest.response.AuthoredChallengesResponse
import com.aleks.aleksiev.codewars.domain.rest.response.CompletedChallengesResponse
import com.aleks.aleksiev.codewars.domain.rest.response.MemberSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserController {

    @GET("users/{userId}")
    fun findUser(@Path("userId") userId: String): ApiResponse<MemberSearchResponse>

    @GET("users/{userId}/code-completedChallenges/completed")
    fun fetchUserCompletedChallenges(@Path("userId") userId: String, @Query("page") pageId: Int): ApiResponse<CompletedChallengesResponse>

    @GET("users/{userId}/code-challenges/authored")
    fun fetchUserAuthoredChallenges(@Path("userId") userId: String): ApiResponse<AuthoredChallengesResponse>
}