package com.aleks.aleksiev.codewars.presentation.challenges

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.domain.model.ChallengeDomainModel
import com.aleks.aleksiev.codewars.domain.model.ChallengesDomainModel
import com.aleks.aleksiev.codewars.domain.usecase.challenges.ChallengesUseCase
import com.aleks.aleksiev.codewars.presentation.BaseTest
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeModel
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeType
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock

class ChallengesViewModelTest : BaseTest() {

    @Mock
    private lateinit var challengesUseCase: ChallengesUseCase
    @Mock
    private lateinit var challengesObserver: Observer<PagedList<ChallengeModel>>

    @InjectMocks
    private lateinit var challengesViewModel: ChallengesViewModel

    private val challengeDomainModel = ChallengeDomainModel(
        challengesGroupId = 1,
        challengeId = "test_challenge_id",
        challengeName = "Test Challenge Name",
        completedAt = "Today",
        challengeDescription = "Description"
    )

    private val challengesDomainModel = ChallengesDomainModel(
        totalPages = 1,
        currentPage = 1,
        challenges = listOf(challengeDomainModel)
    )

    @Test
    fun givenSelectedCompletedChallengesWhenFetchChallengesThenFetchCompletedChallengesIsCalled() {
        whenever(challengesUseCase.fetchCompletedChallenges(any(), any())).then { Single.just(challengesDomainModel) }

        challengesViewModel.selectedItem = R.id.action_completed_challenges
        challengesViewModel.fetchChallenges(1)
        triggerActions()

        verify(challengesUseCase, times(1)).fetchCompletedChallenges(any(), any())
    }

    @Test
    fun givenSelectedAuthoredChallengesWhenFetchChallengesThenFetchAuthoredChallengesIsCalled() {
        whenever(challengesUseCase.fetchAuthoredChallenges(any())).then { Single.just(challengesDomainModel) }

        challengesViewModel.selectedItem = R.id.action_authored_challenges
        challengesViewModel.fetchChallenges(1)
        triggerActions()

        verify(challengesUseCase, times(1)).fetchAuthoredChallenges(any())
    }

    @Test
    fun givenSelectedCompletedChallengesThenChallengeTypeValueEqualsCompleted() {
        challengesViewModel.selectedItem = R.id.action_completed_challenges
        assert(challengesViewModel.challengeType == ChallengeType.Completed){ "Unexpected challengeType value"}
    }

    @Test
    fun givenSelectedAuthoredChallengesThenChallengeTypeValueEqualsAuthored() {
        challengesViewModel.selectedItem = R.id.action_authored_challenges
        assert(challengesViewModel.challengeType == ChallengeType.Authored){ "Unexpected challengeType value"}
    }

    @Test
    fun givenSelectedCompletedChallengesWhenFetchChallengesThenChallengesListIsUpdated() {
        whenever(challengesUseCase.fetchCompletedChallenges(any(), any())).then { Single.just(challengesDomainModel) }

        challengesViewModel.selectedItem = R.id.action_completed_challenges

        challengesViewModel.challenges.observeForever(challengesObserver)

        challengesViewModel.fetchChallenges(1)
        triggerActions()

        verify(challengesObserver, times(1)).onChanged(any())
    }

    @Test
    fun givenSelectedAuthoredChallengesWhenFetchChallengesThenChallengesListIsUpdated() {
        whenever(challengesUseCase.fetchAuthoredChallenges(any())).then { Single.just(challengesDomainModel) }

        challengesViewModel.selectedItem = R.id.action_authored_challenges

        challengesViewModel.challenges.observeForever(challengesObserver)

        challengesViewModel.fetchChallenges(1)
        triggerActions()

        verify(challengesObserver, times(1)).onChanged(any())
    }
}