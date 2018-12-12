package com.aleks.aleksiev.codewars.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.aleks.aleksiev.codewars.presentation.common.navigator.Navigation
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var navigation: Navigation
    @Mock
    lateinit var schedulersFacade: SchedulersFacade
    @Mock
    lateinit var renderStateObserver: Observer<RenderState>

    private val testScheduler = TestScheduler()

    @Before
    open fun setUp() {

        MockitoAnnotations.initMocks(this)

        whenever(schedulersFacade.ioScheduler()).then { testScheduler }
        whenever(schedulersFacade.mainThreadScheduler()).then { testScheduler }
        whenever(schedulersFacade.computationScheduler()).then { testScheduler }
    }

    protected fun triggerActions() {
        testScheduler.triggerActions()
    }
}