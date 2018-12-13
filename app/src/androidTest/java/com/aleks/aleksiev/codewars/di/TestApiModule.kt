package com.aleks.aleksiev.codewars.di

import com.aleks.aleksiev.codewars.domain.rest.UserController
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class TestApiModule {

    @Provides
    @Singleton
    fun providesUserController(retrofit: Retrofit): UserController = Mockito.mock(UserController::class.java)
}