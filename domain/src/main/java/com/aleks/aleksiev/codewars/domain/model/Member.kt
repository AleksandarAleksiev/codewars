package com.aleks.aleksiev.codewars.domain.model

data class Member(
    val memberName: String,
    val memberUserName: String,
    val honor: String,
    val clan: String,
    val leaderBoardPosition: Int,
    val rank: Int,
    val bestLanguage: String,
    val id: Long
)