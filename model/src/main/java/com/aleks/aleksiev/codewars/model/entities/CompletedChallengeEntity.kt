package com.aleks.aleksiev.codewars.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aleks.aleksiev.codewars.model.entities.model.CompletedChallenges

@Entity(tableName = "completed_challenge")
data class CompletedChallengeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,
    @ColumnInfo(name = "user_id")
    var userId: Long,
    @ColumnInfo(name = "total_pages")
    val totalPages: Int,
    @ColumnInfo(name = "total_items")
    val totalItems: Int,
    @ColumnInfo(name = "page")
    val page: Int,
    @ColumnInfo(name = "challenges")
    val challenges: CompletedChallenges

)