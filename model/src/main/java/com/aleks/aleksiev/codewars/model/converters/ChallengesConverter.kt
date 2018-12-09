package com.aleks.aleksiev.codewars.model.converters

import android.arch.persistence.room.TypeConverter
import com.aleks.aleksiev.codewars.model.entities.model.Challenges
import com.google.gson.GsonBuilder

object ChallengesConverter {

    private val gson by lazy {
        GsonBuilder().create()
    }

    @TypeConverter
    @JvmStatic
    fun toChallenge(challenges: String): Challenges {
        return gson.fromJson(challenges, Challenges::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun toString(challenges: Challenges): String {
        return gson.toJson(challenges)
    }
}