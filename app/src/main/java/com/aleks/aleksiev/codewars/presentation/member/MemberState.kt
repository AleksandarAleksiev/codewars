package com.aleks.aleksiev.codewars.presentation.member

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.aleks.aleksiev.codewars.BR
import com.aleks.aleksiev.codewars.utils.Constants
import javax.inject.Inject

class MemberState @Inject constructor() : BaseObservable() {

    @get:Bindable
    var userName: String = Constants.EMPTY_STRING
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.userName)
                validateInput()
            }
        }

    @get:Bindable
    var password: String = Constants.EMPTY_STRING
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.password)
                validateInput()
            }
        }

    @get:Bindable
    var inputValid: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.inputValid)
            }
        }

    private fun validateInput() {
        inputValid = password.isNotBlank() && userName.isNotBlank()
    }
}