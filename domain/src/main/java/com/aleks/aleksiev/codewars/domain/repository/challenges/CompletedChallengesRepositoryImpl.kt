package com.aleks.aleksiev.codewars.domain.repository.challenges

import com.aleks.aleksiev.codewars.domain.rest.UserController
import com.aleks.aleksiev.codewars.domain.rest.response.ApiErrorResponse
import com.aleks.aleksiev.codewars.domain.rest.response.ApiResponse
import com.aleks.aleksiev.codewars.domain.rest.response.ApiSuccessResponse
import com.aleks.aleksiev.codewars.domain.rest.response.CompletedChallengesResponse
import com.aleks.aleksiev.codewars.model.CodewarsDatabase
import com.aleks.aleksiev.codewars.model.entities.CompletedChallenge
import com.aleks.aleksiev.codewars.model.entities.model.Challenges
import com.aleks.aleksiev.codewars.model.repository.CompletedChallengesRepository
import com.aleks.aleksiev.codewars.model.repository.Repository
import javax.inject.Inject
import com.aleks.aleksiev.codewars.domain.rest.model.Challenge as ChallengeResponse
import com.aleks.aleksiev.codewars.model.entities.model.Challenge as ChallengeEntity

class CompletedChallengesRepositoryImpl  @Inject constructor(
    private val repository: Repository,
    private val database: CodewarsDatabase,
    private val userController: UserController
) : CompletedChallengesRepository, Repository by repository {

    override fun fetchCompletedChallenges(userId: Long, userName: String, page: Int): CompletedChallenge {
        var result = fetchFromDB(userId, page)
        if(result == null) {
            result = fetchNetwork(userId, userName, page)
        }

        return result
    }

    override fun saveCompletedChallenges(completedChallenge: CompletedChallenge): Long {
        return database.completedChallengeDao().insert(completedChallenge)
    }

    private fun cacheResponse(userId: Long, page: Int, responseBody: CompletedChallengesResponse): CompletedChallenge {
        val completedChallenge = toCompletedChallenge(userId, page, responseBody)
        completedChallenge.id = saveCompletedChallenges(completedChallenge)
        return completedChallenge
    }

    private fun fetchFromDB(userId: Long, page: Int): CompletedChallenge? {
        return database.completedChallengeDao().loadUserCompletedChalenges(userId, page)
    }

    private fun fetchNetwork(userId: Long, userName: String, page: Int): CompletedChallenge {
        val response = userController.fetchUserCompletedChallenges(userId = userName, pageId = page)
        return onApiResponse(userId, page, response)
    }

    private fun onApiResponse(userId: Long, page: Int, response: ApiResponse<CompletedChallengesResponse>): CompletedChallenge {
        return when (response) {
            is ApiSuccessResponse -> cacheResponse(userId, page, response.body)
            is ApiErrorResponse -> throw Exception(response.errorMessage)
            else -> throw Exception()
        }
    }

    private fun toCompletedChallenge(userId: Long, page: Int, completedChallengesResponse: CompletedChallengesResponse): CompletedChallenge {
        val challenges = completedChallengesResponse.challenges.map { toChallengeEntity(it) }
        return CompletedChallenge(
            userId = userId,
            page = page,
            totalPages = completedChallengesResponse.totalPages,
            totalItems = completedChallengesResponse.totalItems,
            challenges = Challenges(challenges = challenges)
        )
    }

    private fun toChallengeEntity(challenge: ChallengeResponse): ChallengeEntity {
        return ChallengeEntity(
            challengeId = challenge.challengeId,
            challengeName = challenge.challengeName,
            challengeSlug = challenge.challengeSlug,
            completedAt = challenge.completedAt,
            completedLanguages = challenge.completedLanguages
        )
    }
}