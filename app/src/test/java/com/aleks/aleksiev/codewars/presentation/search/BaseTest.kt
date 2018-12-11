package com.aleks.aleksiev.codewars.presentation.search

import com.aleks.aleksiev.codewars.presentation.common.navigator.Navigator
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseTest {

    @Mock
    lateinit var navigator: Navigator
    @Mock
    lateinit var schedulersFacade: SchedulersFacade

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