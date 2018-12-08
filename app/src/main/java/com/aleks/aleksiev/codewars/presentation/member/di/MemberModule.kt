package com.aleks.aleksiev.codewars.presentation.member.di

import android.arch.lifecycle.ViewModel
import com.aleks.aleksiev.codewars.di.annotation.ViewModelKey
import com.aleks.aleksiev.codewars.presentation.member.MemberViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MemberModule {

    @Binds
    @IntoMap
    @ViewModelKey(MemberViewModel::class)
    abstract fun bindsLoginViewodel(memberViewModel: MemberViewModel): ViewModel
}