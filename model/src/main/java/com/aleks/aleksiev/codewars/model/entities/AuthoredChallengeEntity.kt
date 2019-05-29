package com.aleks.aleksiev.codewars.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aleks.aleksiev.codewars.model.entities.model.AuthoredChallenges

@Entity(tableName = "authored_challenge")
data class AuthoredChallengeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,
    @ColumnInfo(name = "user_id")
    var userId: Long,
    @ColumnInfo(name = "challenges")
    val challenges: AuthoredChallenges
)