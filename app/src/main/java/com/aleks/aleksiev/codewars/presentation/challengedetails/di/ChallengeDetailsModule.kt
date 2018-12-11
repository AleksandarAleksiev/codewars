package com.aleks.aleksiev.codewars.presentation.challengedetails.di

import android.arch.lifecycle.ViewModel
import com.aleks.aleksiev.codewars.di.annotation.ViewModelKey
import com.aleks.aleksiev.codewars.presentation.challengedetails.ChallengeDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChallengeDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ChallengeDetailsViewModel::class)
    abstract fun bindsChallengeDetailsViewModel(challengeDetailsViewModel: ChallengeDetailsViewModel): ViewModel
}