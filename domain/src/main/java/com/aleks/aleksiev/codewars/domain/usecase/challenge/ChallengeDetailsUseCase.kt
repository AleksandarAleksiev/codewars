package com.aleks.aleksiev.codewars.domain.usecase.challenge

import com.aleks.aleksiev.codewars.domain.model.ChallengeDetailsDomainModel
import io.reactivex.Single

interface ChallengeDetailsUseCase {

    fun getAuthoredChallengeDetails(challengesGroupId: Long, challengeId: String): Single<ChallengeDetailsDomainModel>
    fun getCompletedChallengeDetails(challengesGroupId: Long, challengeId: String): Single<ChallengeDetailsDomainModel>
}