package com.aleks.aleksiev.codewars.domain.repository.challenges

import com.aleks.aleksiev.codewars.domain.rest.UserController
import com.aleks.aleksiev.codewars.domain.rest.response.ApiErrorResponse
import com.aleks.aleksiev.codewars.domain.rest.response.ApiResponse
import com.aleks.aleksiev.codewars.domain.rest.response.ApiSuccessResponse
import com.aleks.aleksiev.codewars.domain.rest.response.CompletedChallengesResponse
import com.aleks.aleksiev.codewars.model.CodewarsDatabase
import com.aleks.aleksiev.codewars.model.entities.CompletedChallengeEntity
import com.aleks.aleksiev.codewars.model.entities.model.CompletedChallenges
import com.aleks.aleksiev.codewars.model.repository.CompletedChallengesRepository
import com.aleks.aleksiev.codewars.model.repository.Repository
import javax.inject.Inject
import com.aleks.aleksiev.codewars.domain.rest.model.Challenge as ChallengeResponse
import com.aleks.aleksiev.codewars.model.entities.model.CompletedChallenge as ChallengeEntity

class CompletedChallengesRepositoryImpl  @Inject constructor(
    private val repository: Repository,
    private val database: CodewarsDatabase,
    private val userController: UserController
) : CompletedChallengesRepository, Repository by repository {

    override fun fetchCompletedChallenges(userId: Long, userName: String, page: Int): CompletedChallengeEntity {
        var result = fetchFromDB(userId, page)
        if(result == null) {
            result = fetchNetwork(userId, userName, page)
        }

        return result
    }

    override fun getCompletedChallenges(challengesGroupId: Long): CompletedChallengeEntity? {
        return database.completedChallengeDao().getCompletedChallengesGroup(challengesGroupId)
    }

    override fun saveCompletedChallenges(completedChallenge: CompletedChallengeEntity): Long {
        return database.completedChallengeDao().insert(completedChallenge)
    }

    private fun cacheResponse(userId: Long, page: Int, responseBody: CompletedChallengesResponse): CompletedChallengeEntity {
        val completedChallenge = toCompletedChallenge(userId, page, responseBody)
        completedChallenge.id = saveCompletedChallenges(completedChallenge)
        return completedChallenge
    }

    private fun fetchFromDB(userId: Long, page: Int): CompletedChallengeEntity? {
        return database.completedChallengeDao().loadUserCompletedChallenges(userId, page)
    }

    private fun fetchNetwork(userId: Long, userName: String, page: Int): CompletedChallengeEntity {
        val response = userController.fetchUserCompletedChallenges(userId = userName, pageId = page)
        return onApiResponse(userId, page, response)
    }

    private fun onApiResponse(userId: Long, page: Int, response: ApiResponse<CompletedChallengesResponse>): CompletedChallengeEntity {
        return when (response) {
            is ApiSuccessResponse -> cacheResponse(userId, page, response.body)
            is ApiErrorResponse -> throw Exception(response.errorMessage)
            else -> throw Exception()
        }
    }

    private fun toCompletedChallenge(userId: Long, page: Int, completedChallengesResponse: CompletedChallengesResponse): CompletedChallengeEntity {
        val challenges = completedChallengesResponse.challenges.map { toChallengeEntity(it) }
        return CompletedChallengeEntity(
            userId = userId,
            page = page,
            totalPages = completedChallengesResponse.totalPages,
            totalItems = completedChallengesResponse.totalItems,
            challenges = CompletedChallenges(completedChallenges = challenges)
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