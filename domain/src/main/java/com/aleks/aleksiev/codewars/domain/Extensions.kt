package com.aleks.aleksiev.codewars.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatString(formatType: String): String {
    val simpleDateFormat = SimpleDateFormat(formatType, Locale.getDefault())
    return simpleDateFormat.format(this)
}