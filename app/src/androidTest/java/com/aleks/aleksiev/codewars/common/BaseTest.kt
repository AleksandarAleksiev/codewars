package com.aleks.aleksiev.codewars.common

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.fragment.app.Fragment
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.di.TestComponent
import com.aleks.aleksiev.codewars.domain.rest.UserController
import com.aleks.aleksiev.codewars.model.CodewarsDatabase
import com.aleks.aleksiev.codewars.presentation.main.MainActivity
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.android.schedulers.AndroidSchedulers
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

    @Inject
    lateinit var codewarsDb: CodewarsDatabase

    private lateinit var testComponent: TestComponent

    private val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val myApp = InstrumentationRegistry.getTargetContext().applicationContext as TestApplication
        testComponent = myApp.component as TestComponent
        testComponent.injectBase(this)

        whenever(schedulersFacade.ioScheduler()).then { testScheduler }
        whenever(schedulersFacade.mainThreadScheduler()).then { AndroidSchedulers.mainThread() }
        whenever(schedulersFacade.computationScheduler()).then { testScheduler }

        testActivityRule.launchActivity(null)
    }

    @After
    fun tearDown() {
        testActivityRule.finishActivity()
        codewarsDb.close()
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
