package com.aleks.aleksiev.codewars.presentation.challenges.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.aleks.aleksiev.codewars.presentation.challenges.ChallengesViewModel
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeModel
import com.aleks.aleksiev.codewars.utils.Constants
import com.aleks.aleksiev.codewars.utils.NetworkState
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade

class CompleteChallengesDataSource (
    private val schedulersFacade: SchedulersFacade,
    private val challengesViewModel: ChallengesViewModel
) : PageKeyedDataSource<Int, ChallengeModel>() {

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ChallengeModel>) {

        networkState.postValue(NetworkState.NetworkStateLoading)
        challengesViewModel.taskInProgress(true)

        challengesViewModel.add(challengesViewModel.fetchCompletedChallenges(1)
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({ challenges ->
                networkState.postValue(NetworkState.NetworkStateSuccess)
                challengesViewModel.taskInProgress(false)
                callback.onResult(challenges, null, 2)
            }, { throwable ->
                val error = NetworkState.NetworkStateError(errorMessage = throwable.localizedMessage)
                networkState.postValue(error)
                challengesViewModel.taskInProgress(false)
            })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ChallengeModel>) {
        networkState.postValue(NetworkState.NetworkStateLoading)

        challengesViewModel.add(challengesViewModel.fetchCompletedChallenges(params.key)
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({ challenges ->
                networkState.postValue(NetworkState.NetworkStateSuccess)
                callback.onResult(challenges, params.key + 1)
            }, { throwable ->
                networkState.postValue(NetworkState.NetworkStateError(throwable.message ?: Constants.EMPTY_STRING))
            })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ChallengeModel>) {

    }
}