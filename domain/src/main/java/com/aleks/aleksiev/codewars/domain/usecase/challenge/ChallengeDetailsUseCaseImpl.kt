package com.aleks.aleksiev.codewars.domain.usecase.challenge

import com.aleks.aleksiev.codewars.domain.Constants
import com.aleks.aleksiev.codewars.domain.formatString
import com.aleks.aleksiev.codewars.domain.model.ChallengeDetailsDomainModel
import com.aleks.aleksiev.codewars.model.entities.model.AuthoredChallenge
import com.aleks.aleksiev.codewars.model.entities.model.CompletedChallenge
import com.aleks.aleksiev.codewars.model.repository.AuthoredChallengesRepository
import com.aleks.aleksiev.codewars.model.repository.CompletedChallengesRepository
import io.reactivex.Single
import javax.inject.Inject

class ChallengeDetailsUseCaseImpl @Inject constructor(
    private val authoredChallengesRepository: AuthoredChallengesRepository,
    private val completedChallengesRepository: CompletedChallengesRepository
) : ChallengeDetailsUseCase {

    override fun getCompletedChallengeDetails(challengesGroupId: Long, challengeId: String): Single<ChallengeDetailsDomainModel> {
        return Single.fromCallable {
            val data = completedChallengesRepository.getCompletedChallenges(challengesGroupId = challengesGroupId)
            val challenge = data?.challenges?.completedChallenges?.firstOrNull { it.challengeId.equals(challengeId, true) }
            if (challenge != null) {
                toChallengeDetailsDomainModel(challenge)
            } else {
                throw Exception()
            }
        }
    }

    override fun getAuthoredChallengeDetails(challengesGroupId: Long, challengeId: String): Single<ChallengeDetailsDomainModel> {
        return Single.fromCallable {
            val data = authoredChallengesRepository.getAuthoredChallenges(challengesGroupId = challengesGroupId)
            val challenge = data?.challenges?.authoredChallenges?.firstOrNull { it.challengeId.equals(challengeId, true) }
            if (challenge != null) {
                toChallengeDetailsDomainModel(challenge)
            } else {
                throw Exception()
            }
        }
    }

    private fun toChallengeDetailsDomainModel(completedChallenge: CompletedChallenge): ChallengeDetailsDomainModel {
        return ChallengeDetailsDomainModel(
            challengeName = completedChallenge.challengeName,
            challengeCompletedAt = completedChallenge.completedAt.formatString(Constants.DATE_FORMAT),
            challengeLanguages = completedChallenge.completedLanguages.reduce { accumulated, language -> String.format("%s, %s", accumulated, language) },
            challengeTags = Constants.EMPTY_STRING,
            challengeDescription = Constants.EMPTY_STRING
        )
    }

    private fun toChallengeDetailsDomainModel(authoredChallenge: AuthoredChallenge): ChallengeDetailsDomainModel {
        return ChallengeDetailsDomainModel(
            challengeName = authoredChallenge.challengeName,
            challengeCompletedAt = Constants.EMPTY_STRING,
            challengeLanguages = authoredChallenge.languages.reduce { accumulated, language -> String.format("%s, %s", accumulated, language) },
            challengeTags = authoredChallenge.tags.reduce { accumulated, tag -> String.format("%s, %s", accumulated, tag) },
            challengeDescription = authoredChallenge.challengeDescription
        )
    }
}