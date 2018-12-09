package com.aleks.aleksiev.codewars.utils

sealed class NetworkState {
    object NetworkStateLoading : NetworkState()
    object NetworkStateSuccess : NetworkState()
    data class NetworkStateError(val errorMessage: String): NetworkState()
}