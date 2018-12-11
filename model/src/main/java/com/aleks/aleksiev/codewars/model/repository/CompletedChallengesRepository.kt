package com.aleks.aleksiev.codewars.model.repository

import com.aleks.aleksiev.codewars.model.entities.CompletedChallengeEntity

interface CompletedChallengesRepository : Repository {

    fun saveCompletedChallenges(completedChallenge: CompletedChallengeEntity): Long
    fun getCompletedChallenges(challengesGroupId: Long): CompletedChallengeEntity?
    fun fetchCompletedChallenges(userId: Long, userName: String, page: Int): CompletedChallengeEntity
}