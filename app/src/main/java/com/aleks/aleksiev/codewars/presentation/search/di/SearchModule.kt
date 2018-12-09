package com.aleks.aleksiev.codewars.presentation.search.di

import android.arch.lifecycle.ViewModel
import com.aleks.aleksiev.codewars.di.annotation.ViewModelKey
import com.aleks.aleksiev.codewars.presentation.search.SearchFragment
import com.aleks.aleksiev.codewars.presentation.search.SearchHistoryUpdateListener
import com.aleks.aleksiev.codewars.presentation.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindsHomeViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    abstract fun bindsSearchHistoryUpdateListener(searchFragment: SearchFragment): SearchHistoryUpdateListener
}