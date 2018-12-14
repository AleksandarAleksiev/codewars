package com.aleks.aleksiev.codewars

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.pressImeActionButton
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.widget.EditText
import com.aleks.aleksiev.codewars.common.BaseTest
import com.aleks.aleksiev.codewars.common.atPosition
import com.aleks.aleksiev.codewars.domain.rest.model.Language
import com.aleks.aleksiev.codewars.domain.rest.model.Languages
import com.aleks.aleksiev.codewars.domain.rest.model.Rank
import com.aleks.aleksiev.codewars.domain.rest.model.Ranks
import com.aleks.aleksiev.codewars.domain.rest.response.ApiErrorResponse
import com.aleks.aleksiev.codewars.domain.rest.response.ApiSuccessResponse
import com.aleks.aleksiev.codewars.domain.rest.response.MemberSearchResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

class SearchFragmentTest : BaseTest() {

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

    private val errorMessage = "Test error message!"
    private val apiSuccessResponse = ApiSuccessResponse(memberSearchResponse)
    private val apiErrorResponse = ApiErrorResponse<MemberSearchResponse>(errorMessage)

    @Test
    fun typeSearchUserText() {
        val searchString = "g964"

        onView(withId(R.id.searchView)).perform(click())

        onView(isAssignableFrom(EditText::class.java)).perform(typeText(searchString), closeSoftKeyboard())

        // Check that the text was changed.
        onView(isAssignableFrom(EditText::class.java)).check(matches(withText(searchString)))
    }

    @Test
    fun populateFoundUser() {
        val searchString = "g964"

        whenever(userController.findUser(any())).then { apiSuccessResponse }

        onView(withId(R.id.searchView)).perform(click())

        onView(isAssignableFrom(EditText::class.java)).perform(typeText(searchString), pressImeActionButton())

        triggerActions()

        Thread.sleep(1000)

        onView(ViewMatchers.withId(R.id.searchHistoryRecyclerView))
            .check(matches(atPosition(0, withId(R.id.memberSearchLayout))))
    }

    @Test
    fun givenErrorWhenSearchingForUserErrorMessageIsShown() {
        val searchString = "g964"

        whenever(userController.findUser(any())).then { apiErrorResponse }

        onView(withId(R.id.searchView)).perform(click())

        onView(isAssignableFrom(EditText::class.java)).perform(typeText(searchString), pressImeActionButton())

        triggerActions()

        Thread.sleep(1000)

        isSnackBarWithTextDisplayed(InstrumentationRegistry.getTargetContext().applicationContext.getString(R.string.error_message, errorMessage))
    }
}