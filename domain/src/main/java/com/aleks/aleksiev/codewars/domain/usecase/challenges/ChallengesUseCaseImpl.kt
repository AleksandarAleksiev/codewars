package com.aleks.aleksiev.codewars.domain.usecase.challenges

import com.aleks.aleksiev.codewars.domain.Constants
import com.aleks.aleksiev.codewars.domain.formatString
import com.aleks.aleksiev.codewars.domain.model.ChallengeDomainModel
import com.aleks.aleksiev.codewars.domain.model.ChallengesDomainModel
import com.aleks.aleksiev.codewars.domain.singleCreateOptional
import com.aleks.aleksiev.codewars.model.entities.AuthoredChallengeEntity
import com.aleks.aleksiev.codewars.model.entities.CompletedChallengeEntity
import com.aleks.aleksiev.codewars.model.repository.AuthoredChallengesRepository
import com.aleks.aleksiev.codewars.model.repository.CompletedChallengesRepository
import com.aleks.aleksiev.codewars.model.repository.MemberSearchRepository
import io.reactivex.Single
import javax.inject.Inject
import com.aleks.aleksiev.codewars.model.entities.model.AuthoredChallenge as AuthoredChallengeEntityModel
import com.aleks.aleksiev.codewars.model.entities.model.CompletedChallenge as CompletedChallengeEntityModel

class ChallengesUseCaseImpl @Inject constructor(
    private val userRepository: MemberSearchRepository,
    private val authoredChallengesRepository: AuthoredChallengesRepository,
    private val completedChallengesRepository: CompletedChallengesRepository
) : ChallengesUseCase {

    override fun fetchAuthoredChallenges(userId: Long): Single<ChallengesDomainModel> {
        return singleCreateOptional {
            val userName = userRepository.getMemberUserName(userId)
            val authoredChallenges = authoredChallengesRepository.fetchAuthoredChallenges(userId, userName)
            if (authoredChallenges.challenges.authoredChallenges.isNullOrEmpty()) {
                throw Exception("Challenges not found") //TODO: Custom error
            }
            toChallengesModel(authoredChallenges)
        }
    }

    override fun fetchCompletedChallenges(userId: Long, page: Int): Single<ChallengesDomainModel> {
        return singleCreateOptional {
            val userName = userRepository.getMemberUserName(userId)
            val completedChallenges = completedChallengesRepository.fetchCompletedChallenges(userId, userName, page)
            if (completedChallenges.challenges.completedChallenges.isNullOrEmpty()) {
                throw Exception("Challenges not found") //TODO: Custom error
            }
            toChallengesModel(completedChallenges)
        }
    }

    private fun toChallengesModel(completedChallenge: CompletedChallengeEntity): ChallengesDomainModel {
        return ChallengesDomainModel(
            totalPages = completedChallenge.totalPages,
            currentPage = completedChallenge.page,
            challenges = completedChallenge.challenges.completedChallenges.map { toChallengeModel(completedChallenge.id, it) }
        )
    }

    private fun toChallengesModel(authoredChallenge: AuthoredChallengeEntity): ChallengesDomainModel {
        return ChallengesDomainModel(
            totalPages = 1,
            currentPage = 1,
            challenges = authoredChallenge.challenges.authoredChallenges.map { toChallengeModel(authoredChallenge.id, it) }
        )
    }

    private fun toChallengeModel(challengeId: Long, completedChallenge: CompletedChallengeEntityModel): ChallengeDomainModel {
        return ChallengeDomainModel(
            challengesGroupId = challengeId,
            challengeId = completedChallenge.challengeId,
            challengeName = completedChallenge.challengeName,
            completedAt = completedChallenge.completedAt.formatString(Constants.DATE_TIME_FORMAT),
            challengeDescription = Constants.EMPTY_STRING
        )
    }

    private fun toChallengeModel(challengeId: Long, authoredChallenge: AuthoredChallengeEntityModel): ChallengeDomainModel {
        return ChallengeDomainModel(
            challengesGroupId = challengeId,
            challengeId = authoredChallenge.challengeId,
            challengeName = authoredChallenge.challengeName,
            completedAt = Constants.EMPTY_STRING,
            challengeDescription = authoredChallenge.challengeDescription
        )
    }
}