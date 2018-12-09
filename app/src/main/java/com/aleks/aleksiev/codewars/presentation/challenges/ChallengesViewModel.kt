package com.aleks.aleksiev.codewars.presentation.challenges

import android.util.Log
import com.aleks.aleksiev.codewars.domain.usecase.CompletedChallengesUseCase
import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.presentation.common.navigator.Navigator
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import io.reactivex.Single
import javax.inject.Inject

class ChallengesViewModel @Inject constructor(
    private val challengesState: ChallengesState,
    private val schedulersFacade: SchedulersFacade,
    private val completedChallengesUseCase: CompletedChallengesUseCase,
    private val navigator: Navigator
) : BaseViewModel(), Navigator by navigator {

    fun fetchCompltedChallenges(userId: Long, page: Int) {
        add(Single.fromCallable { completedChallengesUseCase.fetchCompletedChallenges(userId, page) }
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({challenges ->
                Log.d("", "")
            }, { error ->
                Log.d("", "")
            }))
    }
}