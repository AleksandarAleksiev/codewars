package com.aleks.aleksiev.codewars.presentation

sealed class RenderState {

    data class LoadingState(val isLoading: Boolean) : RenderState()
    data class MessageSuccessState(val message: String) : RenderState()
    data class MessageErrorState(val errorMessage: String) : RenderState()
}