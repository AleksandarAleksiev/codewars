package com.aleks.aleksiev.codewars.presentation.challenges.model

import com.aleks.aleksiev.codewars.presentation.diffutils.DiffUtilItemComparable
import com.aleks.aleksiev.codewars.utils.Constants
import com.aleks.aleksiev.codewars.utils.NetworkState

data class LoadingStateModel(
    private val networkState: NetworkState?
) : DiffUtilItemComparable<LoadingStateModel> {

    val errorMessage: String
        get() {
            return if (networkState is NetworkState.NetworkStateError) {
                networkState.errorMessage
            } else {
                Constants.EMPTY_STRING
            }
        }

    val isLoading: Boolean
        get() {
            return networkState is NetworkState.NetworkStateLoading
        }

    override fun areItemsTheSame(newItem: LoadingStateModel): Boolean {
        return this.networkState == newItem.networkState
    }

    override fun areContentsTheSame(newItem: LoadingStateModel): Boolean {
        return this == newItem
    }
}