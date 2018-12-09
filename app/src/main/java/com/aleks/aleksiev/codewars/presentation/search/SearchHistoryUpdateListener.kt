package com.aleks.aleksiev.codewars.presentation.search

import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMember

interface SearchHistoryUpdateListener {
    fun searchHistoryUpdated(searchHistory: List<FoundMember>)
}