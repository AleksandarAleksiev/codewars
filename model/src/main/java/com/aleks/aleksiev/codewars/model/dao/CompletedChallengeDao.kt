package com.aleks.aleksiev.codewars.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update
import com.aleks.aleksiev.codewars.model.entities.CompletedChallenge

@Dao
interface CompletedChallengeDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(completedChallenge: CompletedChallenge): Long

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun update(completedChallenge: CompletedChallenge)

    @Delete
    fun delete(completedChallenge: CompletedChallenge)
}