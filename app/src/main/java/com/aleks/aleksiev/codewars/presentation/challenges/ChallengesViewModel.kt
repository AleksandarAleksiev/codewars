package com.aleks.aleksiev.codewars.presentation.challenges

import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.presentation.common.navigator.Navigator
import javax.inject.Inject

class ChallengesViewModel @Inject constructor(private val challengesState: ChallengesState,
                                              private val navigator: Navigator
) : BaseViewModel(), Navigator by navigator