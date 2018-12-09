package com.aleks.aleksiev.codewars.domain.rest.response

import com.aleks.aleksiev.codewars.domain.rest.model.Ranks
import com.google.gson.annotations.SerializedName

data class MemberSearchResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val userName: String,
    @SerializedName("honor")
    val honor: String,
    @SerializedName("clan")
    val clan: String,
    @SerializedName("leaderboardPosition")
    val leaderBoardPosition: Int,
    @SerializedName("ranks")
    val ranks: Ranks
)