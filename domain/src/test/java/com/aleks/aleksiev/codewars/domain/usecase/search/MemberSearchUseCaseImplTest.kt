package com.aleks.aleksiev.codewars.domain.usecase.search

import com.aleks.aleksiev.codewars.domain.model.Member
import com.aleks.aleksiev.codewars.domain.model.SortBy
import com.aleks.aleksiev.codewars.model.entities.MemberSearch
import com.aleks.aleksiev.codewars.model.repository.MemberSearchRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import java.util.Date

class MemberSearchUseCaseImplTest : BaseTest() {

    @Mock
    private lateinit var memberSearchRepository: MemberSearchRepository

    @InjectMocks
    private lateinit var memberSearchUseCaseImpl: MemberSearchUseCaseImpl

    private val memberSearch = MemberSearch(
        memberUserName = "Test Member User Name",
        memberName = "Test Member Name",
        honor = "Test Honor",
        clan = "Test Clan",
        leaderBoardPosition = 1,
        id = 1,
        rank = 1,
        bestLanguage = "Kotlin",
        searchedDate = Date()
    )

    @Test
    fun givenValidMemberNameWhenSearchMemberThenMemberIsReturned() {

        whenever(memberSearchRepository.findMember(any())).then { memberSearch }

        val member = memberSearchUseCaseImpl.findMember("Test Member User Name")

        assert(member.id == memberSearch.id) {"Invalid member user name"}
    }

    @Test
    fun givenMembersBeenSearchedWhenQueryTableThenListOfMembersIsReturned() {

        val searchedMembers = listOf(memberSearch)
        whenever(memberSearchRepository.observeMemberSearchSortedByDate(any())).then { Flowable.fromArray(searchedMembers) }

        var members: List<Member>? = null
        memberSearchUseCaseImpl.observeMemberSearch(1, SortBy.Date)
            .subscribeOn(testScheduler)
            .subscribe { members = it }

        triggerActions()

        assert(members?.size == searchedMembers.size) {"Empty search history"}
    }
}