package com.aleks.aleksiev.codewars.presentation.main

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.aleks.aleksiev.codewars.BR
import javax.inject.Inject

class MainState @Inject constructor() : BaseObservable() {

    @get:Bindable
    var taskInProgress: Boolean = false
        set(value) {
            if (value != field) {
                field = value
                notifyPropertyChanged(BR.taskInProgress)
            }
        }
}