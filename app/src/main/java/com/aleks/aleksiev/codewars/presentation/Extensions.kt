package com.aleks.aleksiev.codewars.presentation

import android.app.Activity
import android.content.Context
import androidx.databinding.BindingAdapter
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView

fun Activity.hideKeyboard(focusView: View? = null) {
    val view = focusView ?: currentFocus
    view?.let {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Activity.showKeyboard(focusView: View? = null) {
    val view = focusView ?: currentFocus
    view?.let {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
        inputMethodManager.toggleSoftInputFromWindow(it.windowToken, InputMethodManager.SHOW_IMPLICIT, 0)
    }
}

@BindingAdapter("android:text")
fun TextView.setNumber(value: Int) {

    try {

        var intValue = 0
        if (!this.text.isNullOrBlank()) {

            intValue = Integer.parseInt(this.text.toString())
        }

        if (intValue != value || this.text.isNullOrBlank()) {

            this.text = value.toString()
        }
    } catch (ex: Exception) {

        ex.printStackTrace()
    }
}