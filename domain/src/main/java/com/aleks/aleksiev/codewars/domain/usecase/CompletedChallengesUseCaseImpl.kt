package com.aleks.aleksiev.codewars.domain.usecase

import com.aleks.aleksiev.codewars.domain.datamodel.CompletedChallenge
import com.aleks.aleksiev.codewars.model.repository.CompletedChallengesRepository
import com.aleks.aleksiev.codewars.model.repository.MemberSearchRepository
import javax.inject.Inject
import com.aleks.aleksiev.codewars.model.entities.model.CompletedChallenge as CompletedChallengeEntity

class CompletedChallengesUseCaseImpl @Inject constructor(
    private val userRepository: MemberSearchRepository,
    private val completedChallengesRepository: CompletedChallengesRepository
) : CompletedChallengesUseCase {

    override fun fetchCompletedChallenges(userId: Long, page: Int): List<CompletedChallenge> {
        val userName = userRepository.getMemberUserName(userId)
        val completedChallenge = completedChallengesRepository.fetchCompletedChallenges(userId, userName, page)
        return completedChallenge.challenges.completedChallenges.map { toCompletedChallenge(completedChallenge.id, it) }
    }

    private fun toCompletedChallenge(challengeId: Long, completedChallenge: CompletedChallengeEntity): CompletedChallenge {
        return CompletedChallenge(
            challengeId = challengeId,
            challengeName = completedChallenge.challengeName,
            challengeCompletedAt = completedChallenge.completedAt
        )
    }
}