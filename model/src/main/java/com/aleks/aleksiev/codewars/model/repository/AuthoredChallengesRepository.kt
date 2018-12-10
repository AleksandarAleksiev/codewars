package com.aleks.aleksiev.codewars.model.repository

import com.aleks.aleksiev.codewars.model.entities.AuthoredChallenge

interface AuthoredChallengesRepository : Repository {

    fun saveAuthoredChallenges(authoredChallenge: AuthoredChallenge): Long
    fun fetchAuthoredChallenges(userId: Long, userName: String): AuthoredChallenge
}