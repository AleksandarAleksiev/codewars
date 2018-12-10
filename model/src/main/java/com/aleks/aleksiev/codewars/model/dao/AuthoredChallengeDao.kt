package com.aleks.aleksiev.codewars.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.aleks.aleksiev.codewars.model.entities.AuthoredChallenge

@Dao
interface AuthoredChallengeDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(authoredChallenge: AuthoredChallenge): Long

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun update(authoredChallenge: AuthoredChallenge)

    @Delete
    fun delete(authoredChallenge: AuthoredChallenge)

    @Query("select * from authored_challenge where user_id = :userId")
    fun loadUserCompletedChalenges(userId: Long): AuthoredChallenge?
}