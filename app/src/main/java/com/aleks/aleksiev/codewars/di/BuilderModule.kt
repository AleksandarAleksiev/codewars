package com.aleks.aleksiev.codewars.di

import com.aleks.aleksiev.codewars.di.annotation.ActivityScope
import com.aleks.aleksiev.codewars.presentation.main.MainActivity
import com.aleks.aleksiev.codewars.presentation.main.di.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [MainModule::class,
        FragmentModule::class])
    @ActivityScope
    abstract fun bindMainActivity(): MainActivity
}