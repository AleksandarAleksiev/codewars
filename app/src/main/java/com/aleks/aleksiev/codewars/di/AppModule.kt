package com.aleks.aleksiev.codewars.di

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.aleks.aleksiev.codewars.CodewarsApp
import com.aleks.aleksiev.codewars.di.annotation.AppContext
import com.aleks.aleksiev.codewars.presentation.viewmodel.ViewModelFactory
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacadeImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory = viewModelFactory

    @Provides
    @AppContext
    fun providesAppContext(codewarsApp: CodewarsApp): Context = codewarsApp

    @Provides
    fun schedulerFacade(schedulersFacadeImpl: SchedulersFacadeImpl): SchedulersFacade = schedulersFacadeImpl

    @Provides
    fun providesGson(): Gson = GsonBuilder().create()
}