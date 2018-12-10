package com.aleks.aleksiev.codewars.presentation.challenges.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.aleks.aleksiev.codewars.presentation.challenges.ChallengesViewModel
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeModel
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengesModel
import com.aleks.aleksiev.codewars.utils.Constants
import com.aleks.aleksiev.codewars.utils.NetworkState
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade

class CompleteChallengesDataSource (
    private val schedulersFacade: SchedulersFacade,
    private val challengesViewModel: ChallengesViewModel
) : PageKeyedDataSource<Int, ChallengeModel>() {

    private val firstPage = 1
    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ChallengeModel>) {

        networkState.postValue(NetworkState.NetworkStateLoading)

        challengesViewModel.add(challengesViewModel.fetchChallenges(firstPage)
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({ challenges ->
                networkState.postValue(NetworkState.NetworkStateSuccess)
                callback.onResult(challenges.challenges, null, getNextPage(challenges))
            }, { throwable ->
                val error = NetworkState.NetworkStateError(errorMessage = throwable.localizedMessage)
                networkState.postValue(error)
            })
        )
    }

    private fun getNextPage(challenges: ChallengesModel?): Int? {
        return challenges?.let {
            when {
                it.page < it.totalPages -> it.page + 1
                else -> null
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ChallengeModel>) {
        networkState.postValue(NetworkState.NetworkStateLoading)

        challengesViewModel.add(challengesViewModel.fetchChallenges(params.key)
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({ challenges ->
                networkState.postValue(NetworkState.NetworkStateSuccess)
                callback.onResult(challenges.challenges, getNextPage(challenges))
            }, { throwable ->
                networkState.postValue(NetworkState.NetworkStateError(throwable.message ?: Constants.EMPTY_STRING))
            })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ChallengeModel>) {

    }
}