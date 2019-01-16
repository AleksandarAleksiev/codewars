package com.aleks.aleksiev.codewars.presentation.search

import com.aleks.aleksiev.codewars.domain.model.Member
import com.aleks.aleksiev.codewars.domain.usecase.search.MemberSearchUseCase
import com.aleks.aleksiev.codewars.presentation.BaseTest
import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMember
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argForWhich
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock

class SearchViewModelTest : BaseTest() {

    @Mock
    lateinit var memberSearchUseCase: MemberSearchUseCase

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
        whenever(memberSearchUseCase.observeMemberSearch(any(), any())).then { Flowable.fromArray(emptyList<Member>()) }
        searchViewModel = SearchViewModel(schedulersFacade, memberSearchUseCase)
        searchViewModel.renderState.observeForever(renderStateObserver)
        searchViewModel.members.observeForever(membersObserver)
    }

    @Test
    fun givenValidUserNameMemberIsFound() {
        whenever(memberSearchUseCase.findMember(anyString())).then { member }
        searchViewModel.findMember(memberName)
        triggerActions()

        verify(memberSearchUseCase, times(1)).findMember(any())
    }

    @Test
    fun givenSuccessfulResultWhenLookingForMemberTaskInProgressIsCalledTwice() {
        whenever(memberSearchUseCase.findMember(anyString())).then { member }
        searchViewModel.findMember(memberName)
        triggerActions()

        verify(renderStateObserver, times(2)).onChanged(any())
    }

    @Test
    fun givenErrorResultWhenLookingForMemberRenderErrorIsCalled() {
        whenever(memberSearchUseCase.findMember(anyString())).then { throw Exception("Test exception") }
        searchViewModel.findMember(memberName)
        triggerActions()

        verify(renderStateObserver, times(1)).onChanged(argForWhich { peekContent().message.isNullOrBlank() })
    }

    @Test
    fun searchedMembersListIsUpdated() {

        whenever(memberSearchUseCase.observeMemberSearch(any(), any())).then { Flowable.fromArray(searchHistory) }
        searchViewModel.searchHistory()
        triggerActions()

        verify(membersObserver, times(1)).onChanged(any())
    }

    @Test
    fun whenLoadingSearchedHistoryTaskInProgressIsNotCalled() {
        whenever(memberSearchUseCase.observeMemberSearch(any(), any())).then { Flowable.fromArray(emptyList<Member>()) }
        searchViewModel.searchHistory()
        triggerActions()

        verify(renderStateObserver, times(0)).onChanged(argForWhich { peekContent().message.isNullOrBlank() })
    }

    @Test
    fun givenErrorWhenLoadingSearchedHistoryThenErrorMessageIsRendered() {
        whenever(memberSearchUseCase.observeMemberSearch(any(), any())).then { Flowable.error<FoundMember>(Exception("Error message")) }
        searchViewModel.searchHistory()
        triggerActions()

        verify(renderStateObserver, times(1)).onChanged(argForWhich { !peekContent().message.isNullOrBlank() })
    }
}