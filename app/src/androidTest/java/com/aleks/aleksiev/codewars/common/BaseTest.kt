package com.aleks.aleksiev.codewars.common

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.design.R
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.Fragment
import com.aleks.aleksiev.codewars.di.TestComponent
import com.aleks.aleksiev.codewars.domain.rest.UserController
import com.aleks.aleksiev.codewars.presentation.main.MainActivity
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.schedulers.TestScheduler
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {

    protected val testActivityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    fun rule() = testActivityRule

    @Inject
    lateinit var userController: UserController

    @Inject
    lateinit var schedulersFacade: SchedulersFacade

    private lateinit var testComponent: TestComponent

    private val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val myApp = InstrumentationRegistry.getTargetContext().applicationContext as TestApplication
        testComponent = myApp.component as TestComponent
        testComponent.injectBase(this)

        whenever(schedulersFacade.ioScheduler()).then { testScheduler }
        whenever(schedulersFacade.mainThreadScheduler()).then { testScheduler }
        whenever(schedulersFacade.computationScheduler()).then { testScheduler }

        testActivityRule.launchActivity(null)
    }

    @After
    fun tearDown() {
        testActivityRule.finishActivity()
    }

    fun setupFragment(fragment: Fragment, fragmentTag: String) {
        rule().activity
            .showScreen(fragment, fragmentTag, true, true)
    }

    fun triggerActions() {
        testScheduler.triggerActions()
    }

    protected fun isSnackBarWithTextDisplayed(message: String) {
        Espresso.onView(CoreMatchers.allOf(ViewMatchers.withId(R.id.snackbar_text), ViewMatchers.withText(message)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
