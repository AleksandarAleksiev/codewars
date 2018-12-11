package com.aleks.aleksiev.codewars.domain.repository.challenges

import com.aleks.aleksiev.codewars.domain.rest.UserController
import com.aleks.aleksiev.codewars.domain.rest.model.AuthoredChallenge
import com.aleks.aleksiev.codewars.domain.rest.response.ApiErrorResponse
import com.aleks.aleksiev.codewars.domain.rest.response.ApiResponse
import com.aleks.aleksiev.codewars.domain.rest.response.ApiSuccessResponse
import com.aleks.aleksiev.codewars.domain.rest.response.AuthoredChallengesResponse
import com.aleks.aleksiev.codewars.model.CodewarsDatabase
import com.aleks.aleksiev.codewars.model.entities.AuthoredChallengeEntity
import com.aleks.aleksiev.codewars.model.entities.model.AuthoredChallenges
import com.aleks.aleksiev.codewars.model.repository.AuthoredChallengesRepository
import com.aleks.aleksiev.codewars.model.repository.Repository
import javax.inject.Inject
import com.aleks.aleksiev.codewars.model.entities.model.AuthoredChallenge as AuthoredChallengeEntityModel

class AuthoredChallengesRepositoryImpl @Inject constructor(
    private val repository: Repository,
    private val database: CodewarsDatabase,
    private val userController: UserController
) : AuthoredChallengesRepository, Repository by repository {

    override fun fetchAuthoredChallenges(userId: Long, userName: String): AuthoredChallengeEntity {
        var result = fetchFromDB(userId)
        if (result == null) {
            result = fetchNetwork(userId, userName)
        }
        return result
    }

    override fun saveAuthoredChallenges(authoredChallenge: AuthoredChallengeEntity): Long {
        return database.authoredChallengeDao().insert(authoredChallenge)
    }

    override fun getAuthoredChallenges(challengesGroupId: Long): AuthoredChallengeEntity? {
        return database.authoredChallengeDao().getAuthoredChallengesGroup(challengesGroupId)
    }

    private fun cacheResponse(userId: Long, responseBody: AuthoredChallengesResponse): AuthoredChallengeEntity {
        val authoredChallenge = toAuthoredChallenge(userId, responseBody)
        authoredChallenge.id = saveAuthoredChallenges(authoredChallenge)
        return authoredChallenge
    }

    private fun fetchFromDB(userId: Long): AuthoredChallengeEntity? {
        return database.authoredChallengeDao().getUserAuthoredChalenges(userId)
    }

    private fun fetchNetwork(userId: Long, userName: String): AuthoredChallengeEntity {
        val response = userController.fetchUserAuthoredChallenges(userId = userName)
        return onApiResponse(userId, response)
    }

    private fun onApiResponse(userId: Long, response: ApiResponse<AuthoredChallengesResponse>): AuthoredChallengeEntity {
        return when (response) {
            is ApiSuccessResponse -> cacheResponse(userId, response.body)
            is ApiErrorResponse -> throw Exception(response.errorMessage)
            else -> throw Exception()
        }
    }

    private fun toAuthoredChallenge(userId: Long, authoredChallengesResponse: AuthoredChallengesResponse): AuthoredChallengeEntity {
        val challenges = authoredChallengesResponse.challenges.map { toChallengeEntity(it) }
        return AuthoredChallengeEntity(
            userId = userId,
            challenges = AuthoredChallenges(authoredChallenges = challenges)
        )
    }

    private fun toChallengeEntity(challenge: AuthoredChallenge): AuthoredChallengeEntityModel {
        return AuthoredChallengeEntityModel(
            challengeId = challenge.challengeId,
            challengeName = challenge.challengeName,
            challengeDescription = challenge.challengeDescription,
            challengeRank = challenge.challengeRank,
            challengeRankName = challenge.challengeRankName,
            tags = challenge.challengeTags,
            languages = challenge.challengeLanguages
        )
    }
}