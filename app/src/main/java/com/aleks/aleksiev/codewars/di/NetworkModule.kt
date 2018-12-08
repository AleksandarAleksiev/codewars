package com.aleks.aleksiev.codewars.di

import android.content.Context
import android.util.Log
import com.aleks.aleksiev.codewars.BuildConfig
import com.aleks.aleksiev.codewars.di.annotation.AppContext
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        gson: Gson,
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): Retrofit {

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
            .addInterceptor(httpLoggingInterceptor)
            .cache(cache)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.apiEndppoint)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun providesHttpCache(@AppContext context: Context): Cache {

        val cacheFile = File(context.cacheDir, "httpCache")
        return Cache(cacheFile, 10 * 1024 * 1024)
    }

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("NetworkModule", message) })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}