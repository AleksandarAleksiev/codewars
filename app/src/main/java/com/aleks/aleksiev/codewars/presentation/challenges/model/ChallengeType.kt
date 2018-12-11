package com.aleks.aleksiev.codewars.presentation.challenges.model

enum class ChallengeType {
    Unknown,
    Completed,
    Authored;

    companion object {
        @JvmStatic
        fun fromInt(value: Int): ChallengeType {
            return when (value) {
                Completed.ordinal -> Completed
                Authored.ordinal -> Authored
                else -> Unknown
            }
        }
    }
}