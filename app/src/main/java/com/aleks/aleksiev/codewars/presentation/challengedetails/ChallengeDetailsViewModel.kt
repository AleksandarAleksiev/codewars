package com.aleks.aleksiev.codewars.presentation.challengedetails

import com.aleks.aleksiev.codewars.domain.usecase.challenge.ChallengeDetailsUseCase
import com.aleks.aleksiev.codewars.presentation.RenderState
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeType
import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import javax.inject.Inject

class ChallengeDetailsViewModel @Inject constructor(
    val challengeDetailsState: ChallengeDetailsState,
    private val schedulersFacade: SchedulersFacade,
    private val challengeDetailsUseCase: ChallengeDetailsUseCase
) : BaseViewModel() {

    fun getChallengeDetails() {
        when (challengeDetailsState.challengeType) {
            ChallengeType.Authored -> getAuthoredChallengeDetails(challengeDetailsState.challengesId, challengeDetailsState.challengeId)
            else -> getCompletedChallengeDetails(challengeDetailsState.challengesId, challengeDetailsState.challengeId)
        }
    }

    private fun getCompletedChallengeDetails(challengesGroupId: Long, challengeId: String) {
        renderState(RenderState.LoadingState(true))
        add(challengeDetailsUseCase.getCompletedChallengeDetails(challengesGroupId, challengeId)
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({challengeDetails ->
                renderState(RenderState.LoadingState(false))
            }, {error ->
                renderState(RenderState.LoadingState(false))
            })
        )
    }

    private fun getAuthoredChallengeDetails(challengesGroupId: Long, challengeId: String) {
        renderState(RenderState.LoadingState(true))
        add(challengeDetailsUseCase.getAuthoredChallengeDetails(challengesGroupId, challengeId)
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({challengeDetails ->
                renderState(RenderState.LoadingState(false))
            }, {error ->
                renderState(RenderState.LoadingState(false))
            })
        )
    }
}