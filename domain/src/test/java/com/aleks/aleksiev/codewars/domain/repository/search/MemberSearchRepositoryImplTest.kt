package com.aleks.aleksiev.codewars.domain.repository.search

import com.aleks.aleksiev.codewars.domain.BaseTest
import com.aleks.aleksiev.codewars.domain.model.EmptyResponseException
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.mockito.InjectMocks

class MemberSearchRepositoryImplTest : BaseTest() {

    @InjectMocks
    private lateinit var memberSearchRepositoryImpl: MemberSearchRepositoryImpl

    override fun setUp() {
        super.setUp()
        whenever(database.memberSearchDao()).then { memberSearchDao }
    }

    @Test
    fun givenMemberIsNotFoundInLocalDbWhenSearchMemberThenCodeWarsApiIsCalled() {

        whenever(database.memberSearchDao().getUser((any()))).then { null }
        whenever(userController.findUser(any())).then { apiSuccessResponse }

        memberSearchRepositoryImpl.findMember("Test Member User Name")

        verify(userController, times(1)).findUser(any())
    }

    @Test
    fun givenMemberFoundInLocalDbWhenSearchMemberThenCodeWarsApiIsNotCalled() {

        whenever(database.memberSearchDao().getUser((any()))).then { memberSearch }

        memberSearchRepositoryImpl.findMember("Test Member User Name")

        verify(userController, times(0)).findUser(any())
    }

    @Test(expected = EmptyResponseException::class)
    fun givenNonExistingMemberWhenSearchMemberThenExceptionIsThrown() {

        whenever(database.memberSearchDao().getUser((any()))).then { null }
        whenever(userController.findUser(any())).then { apiEmptyResponse }

        memberSearchRepositoryImpl.findMember("Test Member User Name")
    }
}