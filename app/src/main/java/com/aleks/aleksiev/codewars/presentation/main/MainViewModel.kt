package com.aleks.aleksiev.codewars.presentation.main

import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val viewState: MainState
) : BaseViewModel()