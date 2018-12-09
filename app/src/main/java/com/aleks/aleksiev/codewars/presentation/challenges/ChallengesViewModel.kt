package com.aleks.aleksiev.codewars.presentation.challenges

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.aleks.aleksiev.codewars.domain.datamodel.CompletedChallenge
import com.aleks.aleksiev.codewars.domain.usecase.CompletedChallengesUseCase
import com.aleks.aleksiev.codewars.presentation.challenges.datasource.CompleteChallengesDataSource
import com.aleks.aleksiev.codewars.presentation.challenges.datasource.CompleteChallengesDataSourceFactory
import com.aleks.aleksiev.codewars.presentation.challenges.model.CompletedChallengeModel
import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.presentation.common.navigator.Navigator
import com.aleks.aleksiev.codewars.utils.Constants
import com.aleks.aleksiev.codewars.utils.NetworkState
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import io.reactivex.Single
import javax.inject.Inject

class ChallengesViewModel @Inject constructor(
    private val userIdProvider: UserIdProvider,
    private val schedulersFacade: SchedulersFacade,
    private val completedChallengesUseCase: CompletedChallengesUseCase,
    private val navigator: Navigator
) : BaseViewModel(), Navigator by navigator {

    var completedChallenges: LiveData<PagedList<CompletedChallengeModel>>

    private val sourceFactory: CompleteChallengesDataSourceFactory by lazy {
        CompleteChallengesDataSourceFactory(schedulersFacade, this)
    }

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(Constants.PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        completedChallenges = LivePagedListBuilder<Int, CompletedChallengeModel>(sourceFactory, config).build()

    }

    fun fetchCompletedChallenges(page: Int): Single<List<CompletedChallengeModel>> {
        return Single.fromCallable {
            completedChallengesUseCase.fetchCompletedChallenges(userIdProvider.getUserId(), page)
                .map { toCompletedChallengeModel(it) }
        }
    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<CompleteChallengesDataSource, NetworkState>(
        sourceFactory.completeChallengesDataSourceLiveData) { it.networkState }

    private fun toCompletedChallengeModel(completeChallenge: CompletedChallenge): CompletedChallengeModel {
        return CompletedChallengeModel(
            challengeId = completeChallenge.challengeId,
            challengeName = completeChallenge.challengeName,
            challengeCompletedAt = completeChallenge.challengeCompletedAt
        )
    }
}