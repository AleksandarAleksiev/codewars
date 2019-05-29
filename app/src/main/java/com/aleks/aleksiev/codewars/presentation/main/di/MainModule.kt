package com.aleks.aleksiev.codewars.presentation.main.di

import androidx.lifecycle.ViewModel
import com.aleks.aleksiev.codewars.di.annotation.ViewModelKey
import com.aleks.aleksiev.codewars.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel
}