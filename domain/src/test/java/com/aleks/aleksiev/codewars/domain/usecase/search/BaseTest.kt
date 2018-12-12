package com.aleks.aleksiev.codewars.domain.usecase.search

import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class BaseTest {

    val testScheduler = TestScheduler()

    @Before
    open fun setUp() {

        MockitoAnnotations.initMocks(this)
    }

    protected fun triggerActions() {
        testScheduler.triggerActions()
    }
}