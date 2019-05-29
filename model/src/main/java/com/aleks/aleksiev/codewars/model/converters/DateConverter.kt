package com.aleks.aleksiev.codewars.model.converters

import androidx.room.TypeConverter
import java.util.*

object DateConverter {

    @TypeConverter
    @JvmStatic
    fun toDate(dateTime: Long): Date {
        return Date(dateTime)
    }

    @TypeConverter
    @JvmStatic
    fun toInteger(dateTime: Date): Long {
        return dateTime.time
    }
}