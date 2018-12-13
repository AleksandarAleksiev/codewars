package com.aleks.aleksiev.codewars.di

import com.aleks.aleksiev.codewars.domain.rest.UserController
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun providesUserController(retrofit: Retrofit): UserController = retrofit.create(UserController::class.java)
}