package com.aleks.aleksiev.codewars.presentation.search

import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.presentation.common.navigator.Navigator
import javax.inject.Inject

class SearchViewModel @Inject constructor(val searchState: SearchState,
                                          private val navigator: Navigator
) : BaseViewModel(), Navigator by navigator