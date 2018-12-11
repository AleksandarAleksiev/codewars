package com.aleks.aleksiev.codewars.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
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