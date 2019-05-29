package com.aleks.aleksiev.codewars.presentation.challenges

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.annotation.IdRes
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.domain.model.ChallengeDomainModel
import com.aleks.aleksiev.codewars.domain.model.ChallengesDomainModel
import com.aleks.aleksiev.codewars.domain.usecase.challenges.ChallengesUseCase
import com.aleks.aleksiev.codewars.presentation.challenges.datasource.CompleteChallengesDataSource
import com.aleks.aleksiev.codewars.presentation.challenges.datasource.CompleteChallengesDataSourceFactory
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeModel
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeType
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengesModel
import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.utils.Constants
import com.aleks.aleksiev.codewars.utils.NetworkState
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import io.reactivex.Single
import javax.inject.Inject

class ChallengesViewModel @Inject constructor(
    private val schedulersFacade: SchedulersFacade,
    private val challengesUseCase: ChallengesUseCase
) : BaseViewModel() {

    @IdRes
    var selectedItem: Int = R.id.action_completed_challenges
    var userId: Long = -1
    var challenges: LiveData<PagedList<ChallengeModel>>
    val challengeType: ChallengeType
        get() {
            return when (selectedItem) {
                R.id.action_authored_challenges -> ChallengeType.Authored
                else -> ChallengeType.Completed
            }
        }

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

    override fun onCleared() {
        super.onCleared()
        this.dispose()
    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<CompleteChallengesDataSource, NetworkState>(
        sourceFactory.completeChallengesDataSourceLiveData) { it.networkState }

    fun fetchChallenges(page: Int): Single<ChallengesModel> {
        return when (challengeType) {
            ChallengeType.Authored -> fetchAuthoredChallenges()
            ChallengeType.Completed -> fetchCompletedChallenges(page)
            else -> throw IllegalArgumentException("Unknown type of challenge")
        }
    }

    fun invalidate(@IdRes itemId: Int) {
        if (itemId != this.selectedItem) {
            dispose()
            this.selectedItem = itemId
            sourceFactory.completeChallengesDataSourceLiveData.value?.invalidate()
        }
    }

    fun retry() {
        sourceFactory.completeChallengesDataSourceLiveData.value?.retry()
    }

    private fun fetchCompletedChallenges(page: Int): Single<ChallengesModel> {
        return challengesUseCase.fetchCompletedChallenges(userId, page)
            .map { toChallengesModel(it) }
    }

    private fun fetchAuthoredChallenges(): Single<ChallengesModel> {
        return challengesUseCase.fetchAuthoredChallenges(userId)
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
            challengesGroupId = completeChallenge.challengesGroupId,
            challengeId = completeChallenge.challengeId,
            challengeName = completeChallenge.challengeName,
            completedAt = completeChallenge.completedAt,
            challengeDescription = completeChallenge.challengeDescription.substring(0, Math.min(Constants.MAX_DISPLAY_CHALLENGES_DESCR, completeChallenge.challengeDescription.length))
        )
    }
}