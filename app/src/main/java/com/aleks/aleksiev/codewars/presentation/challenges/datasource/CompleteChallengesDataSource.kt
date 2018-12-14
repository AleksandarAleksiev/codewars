package com.aleks.aleksiev.codewars.presentation.challenges.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.aleks.aleksiev.codewars.presentation.RenderState
import com.aleks.aleksiev.codewars.presentation.challenges.ChallengesViewModel
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeModel
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengesModel
import com.aleks.aleksiev.codewars.utils.Constants
import com.aleks.aleksiev.codewars.utils.Event
import com.aleks.aleksiev.codewars.utils.NetworkState
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class CompleteChallengesDataSource (
    private val schedulersFacade: SchedulersFacade,
    private val challengesViewModel: ChallengesViewModel
) : PageKeyedDataSource<Int, ChallengeModel>() {

    private val firstPage = 1
    val networkState = MutableLiveData<NetworkState>()

    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ChallengeModel>) {

        challengesViewModel.renderState(Event(RenderState(true)))

        challengesViewModel.add(challengesViewModel.fetchChallenges(firstPage)
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({ challenges ->
                setRetry(null)
                challengesViewModel.renderState(Event(RenderState(false)))
                callback.onResult(challenges.challenges, null, getNextPage(challenges))
            }, { throwable ->
                challengesViewModel.renderState(Event(RenderState(false, throwable.message)))
                setRetry(Action { loadInitial(params, callback) })
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
                setRetry(null)
                networkState.postValue(NetworkState.NetworkStateSuccess)
                callback.onResult(challenges.challenges, getNextPage(challenges))
            }, { throwable ->
                networkState.postValue(NetworkState.NetworkStateError(throwable.message ?: Constants.EMPTY_STRING))
                setRetry(Action { loadAfter(params, callback) })
            })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ChallengeModel>) {

    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }

    fun retry() {
        if (retryCompletable != null) {
            challengesViewModel.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ }, { throwable -> throwable.printStackTrace() }))
        }
    }
}