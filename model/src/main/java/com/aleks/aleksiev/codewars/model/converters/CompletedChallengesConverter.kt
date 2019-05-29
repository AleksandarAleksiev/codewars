package com.aleks.aleksiev.codewars.model.converters

import androidx.room.TypeConverter
import com.aleks.aleksiev.codewars.model.entities.model.CompletedChallenges
import com.google.gson.GsonBuilder

object CompletedChallengesConverter {

    private val gson by lazy {
        GsonBuilder().create()
    }

    @TypeConverter
    @JvmStatic
    fun toChallenge(challenges: String): CompletedChallenges {
        return gson.fromJson(challenges, CompletedChallenges::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun toString(completedChallenges: CompletedChallenges): String {
        return gson.toJson(completedChallenges)
    }
}