package com.aleks.aleksiev.codewars.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.aleks.aleksiev.codewars.model.entities.MemberSearch

@Dao
interface MemberSearchDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(address: MemberSearch): Long

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun update(memberSearch: MemberSearch)

    @Delete
    fun delete(memberSearch: MemberSearch)
}