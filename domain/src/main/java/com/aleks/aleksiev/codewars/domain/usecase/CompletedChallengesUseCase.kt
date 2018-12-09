package com.aleks.aleksiev.codewars.domain.usecase

import com.aleks.aleksiev.codewars.domain.datamodel.CompletedChallenge

interface CompletedChallengesUseCase {

    fun fetchCompletedChallenges(userId: Long, page: Int): List<CompletedChallenge>
}

