package com.aleks.aleksiev.codewars.model.repository

interface Repository {
    fun <T> executeInTransaction(transactionBlock: () -> T): T
}