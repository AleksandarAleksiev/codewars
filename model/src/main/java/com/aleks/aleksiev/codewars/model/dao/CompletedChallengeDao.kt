package com.aleks.aleksiev.codewars.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
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