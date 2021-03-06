package com.aleks.aleksiev.codewars.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMember
import com.aleks.aleksiev.codewars.utils.Event
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
    lateinit var schedulersFacade: SchedulersFacade
    @Mock
    lateinit var renderStateObserver: Observer<Event<RenderState>>
    @Mock
    lateinit var membersObserver: Observer<List<FoundMember>>

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