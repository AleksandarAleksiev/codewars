package com.aleks.aleksiev.codewars.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aleks.aleksiev.codewars.model.entities.AuthoredChallengeEntity

@Dao
interface AuthoredChallengeDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(authoredChallenge: AuthoredChallengeEntity): Long

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun update(authoredChallenge: AuthoredChallengeEntity)

    @Delete
    fun delete(authoredChallenge: AuthoredChallengeEntity)

    @Query("select * from authored_challenge where user_id = :userId")
    fun getUserAuthoredChalenges(userId: Long): AuthoredChallengeEntity

    @Query("select * from authored_challenge where id = :challengesId")
    fun getAuthoredChallengesGroup(challengesId: Long): AuthoredChallengeEntity?
}