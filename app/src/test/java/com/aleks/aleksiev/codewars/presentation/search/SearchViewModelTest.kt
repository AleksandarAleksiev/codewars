package com.aleks.aleksiev.codewars.presentation.search

import com.aleks.aleksiev.codewars.domain.model.Member
import com.aleks.aleksiev.codewars.domain.usecase.search.MemberSearchUseCase
import com.aleks.aleksiev.codewars.presentation.BaseTest
import com.aleks.aleksiev.codewars.presentation.RenderState
import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMember
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class SearchViewModelTest : BaseTest() {

    @Mock
    lateinit var memberSearchUseCase: MemberSearchUseCase
    @Mock
    lateinit var searchHistoryUpdateListener: SearchHistoryUpdateListener

    @InjectMocks
    private lateinit var searchViewModel: SearchViewModel

    private val member = Member(
        memberName = "TestMember",
        memberUserName = "test_user",
        honor = "test_honor",
        clan = "test_clan",
        leaderBoardPosition = 1,
        id = 1,
        rank = 1,
        bestLanguage = "Kotlin"
    )

    private val searchLimit = 1
    private val memberName = "member_name"
    private val searchHistory = listOf(member)

    override fun setUp() {
        super.setUp()
        searchViewModel.renderState.observeForever(renderStateObserver)
    }

    @Test
    fun givenValidUserNameMemberIsFound() {
        whenever(memberSearchUseCase.findMember(anyString())).then { member }
        searchViewModel.findMember(memberName)
        triggerActions()

        verify(memberSearchUseCase, times(1)).findMember(any())
    }

    @Test
    fun whenLookingForMemberTaskInProgressIsCalledTwice() {
        whenever(memberSearchUseCase.findMember(anyString())).then { member }
        searchViewModel.findMember(memberName)
        triggerActions()

        verify(renderStateObserver, times(2)).onChanged(any())
    }

    @Test
    fun searchedMembersListIsUpdated() {

        whenever(memberSearchUseCase.observeMemberSearch(any(), any())).then { Flowable.fromArray(searchHistory) }
        searchViewModel.searchHistory()
        triggerActions()

        verify(searchHistoryUpdateListener, times(1)).searchHistoryUpdated(any())
    }

    @Test
    fun whenLoadingSearchedHistoryTaskInProgressIsCalledTwice() {
        whenever(memberSearchUseCase.observeMemberSearch(any(), any())).then { Flowable.fromArray(emptyArray<FoundMember>()) }
        searchViewModel.searchHistory()
        triggerActions()

        verify(renderStateObserver, times(2)).onChanged(Mockito.any(RenderState.LoadingState::class.java))
    }
}