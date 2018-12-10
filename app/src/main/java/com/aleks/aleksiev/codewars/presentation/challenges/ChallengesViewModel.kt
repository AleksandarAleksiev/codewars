package com.aleks.aleksiev.codewars.presentation.challenges

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.annotation.IdRes
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.domain.datamodel.ChallengeDomainModel
import com.aleks.aleksiev.codewars.domain.datamodel.ChallengesDomainModel
import com.aleks.aleksiev.codewars.domain.usecase.ChallengesUseCase
import com.aleks.aleksiev.codewars.presentation.challenges.datasource.CompleteChallengesDataSource
import com.aleks.aleksiev.codewars.presentation.challenges.datasource.CompleteChallengesDataSourceFactory
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeModel
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengesModel
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
    private val challengesUseCase: ChallengesUseCase,
    private val navigator: Navigator
) : BaseViewModel(), Navigator by navigator {

    @IdRes
    var selectedItem: Int = R.id.action_completed_challenges
    var challenges: LiveData<PagedList<ChallengeModel>>

    private val sourceFactory: CompleteChallengesDataSourceFactory by lazy {
        CompleteChallengesDataSourceFactory(schedulersFacade, this)
    }

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(Constants.PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        challenges = LivePagedListBuilder<Int, ChallengeModel>(sourceFactory, config).build()

    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<CompleteChallengesDataSource, NetworkState>(
        sourceFactory.completeChallengesDataSourceLiveData) { it.networkState }

    fun fetchChallenges(page: Int): Single<ChallengesModel> {
        return when (selectedItem) {
            R.id.action_authored_challenges -> fetchAuthoredChallenges()
            else -> fetchCompletedChallenges(page)
        }
    }

    fun invalidate() {
        sourceFactory.completeChallengesDataSourceLiveData.value?.invalidate()
    }

    private fun fetchCompletedChallenges(page: Int): Single<ChallengesModel> {
        return challengesUseCase.fetchCompletedChallenges(userIdProvider.getUserId(), page)
            .map { toChallengesModel(it) }
    }

    private fun fetchAuthoredChallenges(): Single<ChallengesModel> {
        return challengesUseCase.fetchAuthoredChallenges(userIdProvider.getUserId())
            .map { toChallengesModel(it) }
    }

    private fun toChallengesModel(challenges: ChallengesDomainModel): ChallengesModel {
        return ChallengesModel(
            page = challenges.currentPage,
            totalPages = challenges.totalPages,
            challenges = challenges.challenges.map { toChallengeModel(it) }
        )
    }

    private fun toChallengeModel(completeChallenge: ChallengeDomainModel): ChallengeModel {
        return ChallengeModel(
            challengeId = completeChallenge.challengeId,
            challengeName = completeChallenge.challengeName
        )
    }
}