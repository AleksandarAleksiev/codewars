package com.aleks.aleksiev.codewars.presentation.search.di

import android.arch.lifecycle.ViewModel
import com.aleks.aleksiev.codewars.di.annotation.ViewModelKey
import com.aleks.aleksiev.codewars.presentation.search.SearchFragment
import com.aleks.aleksiev.codewars.presentation.search.SearchViewModel
import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMember
import com.aleks.aleksiev.codewars.utils.ItemClicked
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindsSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    abstract fun bindsFoundMemberItemClicked(searchFragment: SearchFragment): ItemClicked<FoundMember>
}