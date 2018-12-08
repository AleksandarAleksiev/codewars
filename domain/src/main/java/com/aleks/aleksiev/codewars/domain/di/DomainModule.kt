package com.aleks.aleksiev.codewars.domain.di

import com.aleks.aleksiev.codewars.domain.repository.MemberSearchRepositoryImpl
import com.aleks.aleksiev.codewars.domain.repository.RepositoryImpl
import com.aleks.aleksiev.codewars.domain.usecase.search.MemberSearch
import com.aleks.aleksiev.codewars.domain.usecase.search.MemberSearchUseCase
import com.aleks.aleksiev.codewars.model.repository.MemberSearchRepository
import com.aleks.aleksiev.codewars.model.repository.Repository
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
    internal abstract fun bindsSearchUseCase(memberSearch: MemberSearch): MemberSearchUseCase

    @Binds
    internal abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    internal abstract fun bindsMemberSearchRepository(memberSearchRepository: MemberSearchRepositoryImpl): MemberSearchRepository
}