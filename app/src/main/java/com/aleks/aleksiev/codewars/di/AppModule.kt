package com.aleks.aleksiev.codewars.di

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.aleks.aleksiev.codewars.CodewarsApp
import com.aleks.aleksiev.codewars.di.annotation.AppContext
import com.aleks.aleksiev.codewars.presentation.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory = viewModelFactory

    @Provides
    @AppContext
    fun providesAppContext(codewarsApp: CodewarsApp): Context = codewarsApp

}