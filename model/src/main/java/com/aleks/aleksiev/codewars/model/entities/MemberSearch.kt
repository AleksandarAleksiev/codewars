package com.aleks.aleksiev.codewars.model.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "member_search")
data class MemberSearch(
    @ColumnInfo(name = "member_name")
    val memberName: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0
)