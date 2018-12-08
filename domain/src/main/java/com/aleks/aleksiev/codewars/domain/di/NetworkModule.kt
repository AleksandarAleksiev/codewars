package com.aleks.aleksiev.codewars.domain.di

import android.content.Context
import android.util.Log
import com.aleks.aleksiev.codewars.domain.rest.UserController
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@Module
class NetworkModule(private val context: Context, private val apiEndppoint: String) {

    private val cache by lazy {
        val cacheFile = File(context.cacheDir, "httpCache")
        Cache(cacheFile, 10 * 1024 * 1024)
    }

    private val loggingInterceptor by lazy {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("NetworkModule", message) })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }

    private val gson by lazy {
        GsonBuilder().create()
    }

    private val retrofit by lazy {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->

                val original = chain.request()

                // Customize the request
                val request = original
                    .newBuilder()
                    .build()

                val response = chain.proceed(request)

                response
            }
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()

        Retrofit.Builder()
            .baseUrl(apiEndppoint)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun providesUserController(): UserController = retrofit.create(UserController::class.java)
}