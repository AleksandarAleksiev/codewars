package com.aleks.aleksiev.codewars.di

import com.aleks.aleksiev.codewars.presentation.search.SearchFragment
import com.aleks.aleksiev.codewars.presentation.search.di.SearchModule
import com.aleks.aleksiev.codewars.presentation.member.MemberFragment
import com.aleks.aleksiev.codewars.presentation.member.di.MemberModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun bindsSearchFragment(): SearchFragment

    @ContributesAndroidInjector(modules = [MemberModule::class])
    abstract fun bindsMemberFragment(): MemberFragment
}