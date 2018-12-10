package com.aleks.aleksiev.codewars.model.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.aleks.aleksiev.codewars.model.entities.model.AuthoredChallenges

@Entity(tableName = "authored_challenge")
data class AuthoredChallenge(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,
    @ColumnInfo(name = "user_id")
    var userId: Long,
    @ColumnInfo(name = "challenges")
    val challenges: AuthoredChallenges
)