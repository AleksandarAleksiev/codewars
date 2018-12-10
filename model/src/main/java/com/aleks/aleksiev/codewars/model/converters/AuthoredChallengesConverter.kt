package com.aleks.aleksiev.codewars.model.converters

import android.arch.persistence.room.TypeConverter
import com.aleks.aleksiev.codewars.model.entities.model.AuthoredChallenges
import com.google.gson.GsonBuilder

object AuthoredChallengesConverter {

    private val gson by lazy {
        GsonBuilder().create()
    }

    @TypeConverter
    @JvmStatic
    fun toChallenge(challenges: String): AuthoredChallenges {
        return gson.fromJson(challenges, AuthoredChallenges::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun toString(authoredChallenges: AuthoredChallenges): String {
        return gson.toJson(authoredChallenges)
    }
}