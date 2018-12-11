package com.aleks.aleksiev.codewars.model.repository

import com.aleks.aleksiev.codewars.model.entities.AuthoredChallengeEntity

interface AuthoredChallengesRepository : Repository {

    fun saveAuthoredChallenges(authoredChallenge: AuthoredChallengeEntity): Long
    fun fetchAuthoredChallenges(userId: Long, userName: String): AuthoredChallengeEntity
    fun getAuthoredChallenges(challengesGroupId: Long): AuthoredChallengeEntity?
}