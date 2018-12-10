package com.aleks.aleksiev.codewars.model

import com.aleks.aleksiev.codewars.model.dao.AuthoredChallengeDao
import com.aleks.aleksiev.codewars.model.dao.CompletedChallengeDao
import com.aleks.aleksiev.codewars.model.dao.MemberSearchDao

interface CodewarsDatabase {
    fun memberSearchDao(): MemberSearchDao
    fun completedChallengeDao(): CompletedChallengeDao
    fun authoredChallengeDao(): AuthoredChallengeDao

    fun <T>executeInTransaction(transactionBlock: () -> T): T
    fun close()
}