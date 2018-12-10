package com.aleks.aleksiev.codewars.presentation

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

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