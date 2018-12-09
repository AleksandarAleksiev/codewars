package com.aleks.aleksiev.codewars.di

import com.aleks.aleksiev.codewars.presentation.search.SearchFragment
import com.aleks.aleksiev.codewars.presentation.search.di.SearchModule
import com.aleks.aleksiev.codewars.presentation.challenges.ChallengesFragment
import com.aleks.aleksiev.codewars.presentation.challenges.di.ChallengesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun bindsSearchFragment(): SearchFragment

    @ContributesAndroidInjector(modules = [ChallengesModule::class])
    abstract fun bindsMemberFragment(): ChallengesFragment
}