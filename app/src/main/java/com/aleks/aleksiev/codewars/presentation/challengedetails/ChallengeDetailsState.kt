package com.aleks.aleksiev.codewars.presentation.challengedetails

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.aleks.aleksiev.codewars.BR
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeType
import com.aleks.aleksiev.codewars.utils.Constants
import javax.inject.Inject

class ChallengeDetailsState @Inject constructor() : BaseObservable() {

    var challengeId: String = Constants.EMPTY_STRING
    var challengesId: Long = 0L
    var challengeType: ChallengeType = ChallengeType.Unknown

    @get:Bindable
    var challengeName: String = Constants.EMPTY_STRING
    set(value) {
        if (value != field) {
            field = value
            notifyPropertyChanged(BR.challengeName)
        }
    }

    @get:Bindable
    var challengeCompletedAt: String = Constants.EMPTY_STRING
        set(value) {
            if (value != field) {
                field = value
                notifyPropertyChanged(BR.challengeCompletedAt)
            }
        }

    @get:Bindable
    var challengeLanguages: String = Constants.EMPTY_STRING
        set(value) {
            if (value != field) {
                field = value
                notifyPropertyChanged(BR.challengeLanguages)
            }
        }

    @get:Bindable
    var challengeTags: String = Constants.EMPTY_STRING
        set(value) {
            if (value != field) {
                field = value
                notifyPropertyChanged(BR.challengeTags)
            }
        }

    @get:Bindable
    var challengeDescription: String = Constants.EMPTY_STRING
        set(value) {
            if (value != field) {
                field = value
                notifyPropertyChanged(BR.challengeDescription)
            }
        }
}