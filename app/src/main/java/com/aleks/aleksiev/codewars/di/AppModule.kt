package com.aleks.aleksiev.codewars.di

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.aleks.aleksiev.codewars.CodewarsApp
import com.aleks.aleksiev.codewars.di.annotation.AppContext
import com.aleks.aleksiev.codewars.presentation.viewmodel.ViewModelFactory
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacadeImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun providesViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @AppContext
    abstract fun providesAppContext(codewarsApp: CodewarsApp): Context

    @Binds
    abstract fun schedulerFacade(schedulersFacadeImpl: SchedulersFacadeImpl): SchedulersFacade
}