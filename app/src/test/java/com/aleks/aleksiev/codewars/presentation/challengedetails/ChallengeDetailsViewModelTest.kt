package com.aleks.aleksiev.codewars.presentation.challengedetails

import com.aleks.aleksiev.codewars.domain.model.ChallengeDetailsDomainModel
import com.aleks.aleksiev.codewars.domain.usecase.challenge.ChallengeDetailsUseCase
import com.aleks.aleksiev.codewars.presentation.BaseTest
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeType
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mock

class ChallengeDetailsViewModelTest : BaseTest() {

    private val challengeDetailsState = ChallengeDetailsState()

    @Mock
    lateinit var challengeDetailsUseCase: ChallengeDetailsUseCase

    private lateinit var challengeDetailsViewModel: ChallengeDetailsViewModel

    private val challengeDetailsDomainModel = ChallengeDetailsDomainModel(
        challengeName = "Test Challenge",
        challengeCompletedAt = "10/11/2018",
        challengeTags = "tag1, tag2",
        challengeLanguages = "kotlin, java",
        challengeDescription = "Test Challenge description"
    )

    override fun setUp() {
        super.setUp()
        challengeDetailsViewModel = ChallengeDetailsViewModel(challengeDetailsState,
            navigator, schedulersFacade, challengeDetailsUseCase)
    }

    @Test
    fun givenChallengeTypeAuthoredWhenGetChallengeDetailsThenGetAuthoredChallengeDetailsIsCalled() {

        challengeDetailsState.challengeType = ChallengeType.Authored

        whenever(challengeDetailsUseCase.getAuthoredChallengeDetails(any(), any())).then { Single.just(challengeDetailsDomainModel) }

        challengeDetailsViewModel.getChallengeDetails()
        triggerActions()

        verify(challengeDetailsUseCase, times(1)).getAuthoredChallengeDetails(any(), any())
    }

    @Test
    fun givenChallengeTypeAuthoredWhenGetChallengeDetailsThenNavigatorTaskInProgressIsCalled() {

        challengeDetailsState.challengeType = ChallengeType.Authored

        whenever(challengeDetailsUseCase.getAuthoredChallengeDetails(any(), any())).then { Single.just(challengeDetailsDomainModel) }

        challengeDetailsViewModel.getChallengeDetails()
        triggerActions()

        verify(navigator, times(2)).taskInProgress(any())
    }

    @Test
    fun givenChallengeTypeCompletedWhenGetChallengeDetailsThenGetCompletedChallengeDetailsIsCalled() {

        challengeDetailsState.challengeType = ChallengeType.Completed

        whenever(challengeDetailsUseCase.getCompletedChallengeDetails(any(), any())).then { Single.just(challengeDetailsDomainModel) }

        challengeDetailsViewModel.getChallengeDetails()
        triggerActions()

        verify(challengeDetailsUseCase, times(1)).getCompletedChallengeDetails(any(), any())
    }

    @Test
    fun givenChallengeTypeCompletedWhenGetChallengeDetailsThenNavigatorTaskInProgressIsCalled() {

        challengeDetailsState.challengeType = ChallengeType.Completed

        whenever(challengeDetailsUseCase.getCompletedChallengeDetails(any(), any())).then { Single.just(challengeDetailsDomainModel) }

        challengeDetailsViewModel.getChallengeDetails()
        triggerActions()

        verify(navigator, times(2)).taskInProgress(any())
    }
}