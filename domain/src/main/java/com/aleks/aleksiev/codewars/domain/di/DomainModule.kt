package com.aleks.aleksiev.codewars.domain.di

import com.aleks.aleksiev.codewars.domain.repository.RepositoryImpl
import com.aleks.aleksiev.codewars.domain.repository.challenges.AuthoredChallengesRepositoryImpl
import com.aleks.aleksiev.codewars.domain.repository.challenges.CompletedChallengesRepositoryImpl
import com.aleks.aleksiev.codewars.domain.repository.search.MemberSearchRepositoryImpl
import com.aleks.aleksiev.codewars.domain.usecase.ChallengesUseCase
import com.aleks.aleksiev.codewars.domain.usecase.ChallengesUseCaseImpl
import com.aleks.aleksiev.codewars.domain.usecase.search.MemberSearchUseCase
import com.aleks.aleksiev.codewars.domain.usecase.search.MemberSearchUseCaseImpl
import com.aleks.aleksiev.codewars.model.repository.AuthoredChallengesRepository
import com.aleks.aleksiev.codewars.model.repository.CompletedChallengesRepository
import com.aleks.aleksiev.codewars.model.repository.MemberSearchRepository
import com.aleks.aleksiev.codewars.model.repository.Repository
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
    internal abstract fun bindsSearchUseCase(memberSearchUseCaseImpl: MemberSearchUseCaseImpl): MemberSearchUseCase

    @Binds
    internal abstract fun bindsCompletedChallengesUseCase(completedChallengesUseCase: ChallengesUseCaseImpl): ChallengesUseCase

    @Binds
    internal abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    internal abstract fun bindsMemberSearchRepository(memberSearchRepository: MemberSearchRepositoryImpl): MemberSearchRepository

    @Binds
    internal abstract fun bindsCompletedChallengesRepository(completedChallengesRepositoryImpl: CompletedChallengesRepositoryImpl): CompletedChallengesRepository

    @Binds
    internal abstract fun bindsAuthoredChallengesRepository(authoredChallengesRepositoryImpl: AuthoredChallengesRepositoryImpl): AuthoredChallengesRepository
}