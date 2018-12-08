package com.aleks.aleksiev.codewars.model

import com.aleks.aleksiev.codewars.model.dao.MemberSearchDao

interface CodewarsDatabase {
    fun memberSearchDao(): MemberSearchDao

    fun <T>executeInTransaction(transactionBlock: () -> T): T
    fun close()
}