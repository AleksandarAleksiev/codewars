package com.aleks.aleksiev.codewars.domain

import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatString(formatType: String): String {
    val simpleDateFormat = SimpleDateFormat(formatType, Locale.getDefault())
    return simpleDateFormat.format(this)
}

inline fun <T> singleCreateOptional(crossinline singleCallable: () -> T?): Single<T> {
    return Single.create {

        try {
            if (!it.isDisposed) {
                val result = singleCallable()
                if (!it.isDisposed) {
                    if (result == null) {
                        it.tryOnError(IllegalStateException())
                    } else {
                        it.onSuccess(result)
                    }
                }
            }
        } catch (ex: Exception) {
            it.tryOnError(ex)
        }
    }
}