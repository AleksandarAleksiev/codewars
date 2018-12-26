package com.aleks.aleksiev.codewars.domain

import com.aleks.aleksiev.codewars.domain.rest.UserController
import com.aleks.aleksiev.codewars.domain.rest.model.Language
import com.aleks.aleksiev.codewars.domain.rest.model.Languages
import com.aleks.aleksiev.codewars.domain.rest.model.Rank
import com.aleks.aleksiev.codewars.domain.rest.model.Ranks
import com.aleks.aleksiev.codewars.domain.rest.response.ApiEmptyResponse
import com.aleks.aleksiev.codewars.domain.rest.response.ApiSuccessResponse
import com.aleks.aleksiev.codewars.domain.rest.response.MemberSearchResponse
import com.aleks.aleksiev.codewars.model.CodewarsDatabase
import com.aleks.aleksiev.codewars.model.dao.MemberSearchDao
import com.aleks.aleksiev.codewars.model.entities.MemberSearch
import com.aleks.aleksiev.codewars.model.repository.Repository
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.Date

abstract class BaseTest {

    @Mock
    protected lateinit var repository: Repository
    @Mock
    protected lateinit var database: CodewarsDatabase
    @Mock
    protected lateinit var memberSearchDao: MemberSearchDao
    @Mock
    protected lateinit var userController: UserController


    val testScheduler = TestScheduler()

    private val language = Language(
        languageName = "Kotlin",
        score = 1,
        rank = 1
    )
    private val memberSearchResponse = MemberSearchResponse(
        name = "Member Name",
        userName = "Member User Name",
        honor = "Honor",
        clan = "Clan",
        leaderBoardPosition = 1,
        ranks = Ranks(
            rank = Rank(
                rank = 1,
                rankScore = 12,
                rankColour = "colour",
                rankName = "RankName"
            ),
            languages = Languages(listOf(language))
        )
    )

    protected val memberSearch = MemberSearch(
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

    private val errorMessage = "Test error message!"
    protected val apiSuccessResponse = ApiSuccessResponse(memberSearchResponse)
    protected val apiEmptyResponse = ApiEmptyResponse<MemberSearchResponse>()

    @Before
    open fun setUp() {

        MockitoAnnotations.initMocks(this)
    }

    protected fun triggerActions() {
        testScheduler.triggerActions()
    }
}