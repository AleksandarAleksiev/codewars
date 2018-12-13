package com.aleks.aleksiev.codewars.model.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.Date

@Entity(tableName = "member_search")
data class MemberSearch(
    @ColumnInfo(name = "member_user_name")
    var memberUserName: String,
    @ColumnInfo(name = "member_name")
    var memberName: String,
    @ColumnInfo(name = "honor")
    var honor: String,
    @ColumnInfo(name = "clan")
    var clan: String,
    @ColumnInfo(name = "rank")
    var rank: Int,
    @ColumnInfo(name = "best_language")
    var bestLanguage: String,
    @ColumnInfo(name = "leaderboardPosition")
    var leaderBoardPosition: Int,
    @ColumnInfo(name = "searched_on_date")
    var searchedDate: Date,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
)