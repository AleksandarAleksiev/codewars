package com.aleks.aleksiev.codewars.domain.usecase.challenges

import com.aleks.aleksiev.codewars.domain.model.ChallengesDomainModel
import io.reactivex.Single

interface ChallengesUseCase {

    fun fetchAuthoredChallenges(userId: Long): Single<ChallengesDomainModel>
    fun fetchCompletedChallenges(userId: Long, page: Int): Single<ChallengesDomainModel>
}

