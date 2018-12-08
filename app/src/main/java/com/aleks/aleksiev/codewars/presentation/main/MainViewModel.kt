package com.aleks.aleksiev.codewars.presentation.main

import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.presentation.common.navigator.Navigator
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val mainState: MainState,
    private val navigator: Navigator
) : BaseViewModel(), Navigator by navigator