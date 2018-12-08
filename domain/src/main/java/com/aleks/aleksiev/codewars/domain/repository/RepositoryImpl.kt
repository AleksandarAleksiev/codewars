package com.aleks.aleksiev.codewars.domain.repository

import com.aleks.aleksiev.codewars.model.CodewarsDatabase
import com.aleks.aleksiev.codewars.model.repository.Repository
import javax.inject.Inject

internal class RepositoryImpl @Inject constructor(
    private val codewarsDatabase: CodewarsDatabase
) : Repository {
    override fun <T> executeInTransaction(transactionBlock: () -> T): T = codewarsDatabase.executeInTransaction(transactionBlock)
}