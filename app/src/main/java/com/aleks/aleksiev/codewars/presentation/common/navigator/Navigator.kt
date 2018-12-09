package com.aleks.aleksiev.codewars.presentation.common.navigator

interface Navigator {

    fun search()
    fun memberChallenges(memberId: Long)
    fun taskInProgress(inProgress: Boolean)
}