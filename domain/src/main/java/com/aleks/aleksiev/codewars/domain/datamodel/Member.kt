package com.aleks.aleksiev.codewars.domain.datamodel

data class Member(
    val memberName: String,
    val memberUserName: String,
    val honor: String,
    val clan: String,
    val leaderBoardPosition: Int,
    val id: Long
)