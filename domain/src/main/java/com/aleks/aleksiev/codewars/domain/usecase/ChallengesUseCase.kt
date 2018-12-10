package com.aleks.aleksiev.codewars.domain.usecase

import com.aleks.aleksiev.codewars.domain.datamodel.ChallengesDomainModel
import io.reactivex.Single

interface ChallengesUseCase {

    fun fetchAuthoredChallenges(userId: Long): Single<ChallengesDomainModel>
    fun fetchCompletedChallenges(userId: Long, page: Int): Single<ChallengesDomainModel>
}

