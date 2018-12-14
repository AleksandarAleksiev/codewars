package com.aleks.aleksiev.codewars.di

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.aleks.aleksiev.codewars.common.TestApplication
import com.aleks.aleksiev.codewars.di.annotation.AppContext
import com.aleks.aleksiev.codewars.domain.rest.model.Languages
import com.aleks.aleksiev.codewars.presentation.viewmodel.ViewModelFactory
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
class TestAppModule {

    @Provides
    fun providesViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory = viewModelFactory

    @Provides
    @AppContext
    fun providesAppContext(codewarsApp: TestApplication): Context = codewarsApp

    @Provides
    @Singleton
    fun schedulerFacade(): SchedulersFacade = Mockito.mock(SchedulersFacade::class.java)

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Languages::class.java, Languages.getDeserializer())
            .create()
    }
}