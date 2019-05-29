package com.aleks.aleksiev.codewars.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aleks.aleksiev.codewars.model.entities.CompletedChallengeEntity

@Dao
interface CompletedChallengeDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(completedChallenge: CompletedChallengeEntity): Long

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun update(completedChallenge: CompletedChallengeEntity)

    @Delete
    fun delete(completedChallenge: CompletedChallengeEntity)

    @Query("select * from completed_challenge where id = :challengesId")
    fun getCompletedChallengesGroup(challengesId: Long): CompletedChallengeEntity?

    @Query("select * from completed_challenge where user_id = :userId and page = :page")
    fun loadUserCompletedChallenges(userId: Long, page: Int): CompletedChallengeEntity?
}