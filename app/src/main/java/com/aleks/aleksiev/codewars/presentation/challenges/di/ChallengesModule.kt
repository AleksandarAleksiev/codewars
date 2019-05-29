package com.aleks.aleksiev.codewars.presentation.challenges.di

import androidx.lifecycle.ViewModel
import com.aleks.aleksiev.codewars.di.annotation.ViewModelKey
import com.aleks.aleksiev.codewars.presentation.challenges.ChallengesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChallengesModule {

    @Binds
    @IntoMap
    @ViewModelKey(ChallengesViewModel::class)
    abstract fun bindsChallengesViewModel(challengesViewModel: ChallengesViewModel): ViewModel
}