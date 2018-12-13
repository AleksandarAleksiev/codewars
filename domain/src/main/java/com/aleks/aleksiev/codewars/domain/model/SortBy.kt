package com.aleks.aleksiev.codewars.domain.model

enum class SortBy {
    Date,
    Rank;

    companion object {
        @JvmStatic
        fun fromInt(value: Int): SortBy {
            return if (value == SortBy.Rank.ordinal) {
                Rank
            } else {
                Date
            }
        }
    }
}