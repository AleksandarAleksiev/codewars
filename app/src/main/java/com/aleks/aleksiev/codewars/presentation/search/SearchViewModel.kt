package com.aleks.aleksiev.codewars.presentation.search

import android.util.Log
import com.aleks.aleksiev.codewars.domain.usecase.search.MemberSearchUseCase
import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.presentation.common.navigator.Navigator
import com.aleks.aleksiev.codewars.utils.Constants
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import io.reactivex.Single
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchState: SearchState,
                                          private val navigator: Navigator,
                                          private val schedulersFacade: SchedulersFacade,
                                          private val memberSearchUseCase: MemberSearchUseCase
) : BaseViewModel(), Navigator by navigator {

    fun findMember() {
        add(Single.fromCallable { memberSearchUseCase.findMember(Constants.EMPTY_STRING) }
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({member ->
                Log.d("", "")
            }, { error ->
                Log.d("", "")
            }))
    }
}