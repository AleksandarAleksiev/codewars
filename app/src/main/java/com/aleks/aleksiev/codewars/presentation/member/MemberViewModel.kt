package com.aleks.aleksiev.codewars.presentation.member

import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.presentation.common.navigator.Navigator
import java.util.UUID
import javax.inject.Inject

class MemberViewModel @Inject constructor(val memberState: MemberState,
                                          private val navigator: Navigator
) : BaseViewModel(), Navigator by navigator