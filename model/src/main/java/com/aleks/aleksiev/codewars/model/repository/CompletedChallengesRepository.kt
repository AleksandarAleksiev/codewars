package com.aleks.aleksiev.codewars.model.repository

import com.aleks.aleksiev.codewars.model.entities.CompletedChallenge

interface CompletedChallengesRepository {

    fun saveCompletedChallenges(completedChallenge: CompletedChallenge): Long
    fun fetchCompletedChallenges(userId: Long, userName: String, page: Int): CompletedChallenge
}