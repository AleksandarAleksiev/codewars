package com.aleks.aleksiev.codewars.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@Ignore
open class BaseTest {

    private lateinit var codewarsDb: CodewarsDb
    internal val database: CodewarsDatabase
        get() = codewarsDb

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    open fun initTest() {
        codewarsDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), CodewarsDb::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    open fun tearDown() {
        codewarsDb.close()
    }
}