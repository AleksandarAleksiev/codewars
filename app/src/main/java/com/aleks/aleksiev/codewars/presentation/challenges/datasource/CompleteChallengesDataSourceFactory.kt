package com.aleks.aleksiev.codewars.presentation.challenges.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.aleks.aleksiev.codewars.presentation.challenges.ChallengesViewModel
import com.aleks.aleksiev.codewars.presentation.challenges.model.CompletedChallengeModel
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade

class CompleteChallengesDataSourceFactory (
    private val schedulersFacade: SchedulersFacade,
    private val challengesViewModel: ChallengesViewModel
): DataSource.Factory<Int, CompletedChallengeModel>() {

    val completeChallengesDataSourceLiveData = MutableLiveData<CompleteChallengesDataSource>()

    override fun create(): DataSource<Int, CompletedChallengeModel> {
        val completeChallengesDataSource = CompleteChallengesDataSource(schedulersFacade, challengesViewModel)
        completeChallengesDataSourceLiveData.postValue(completeChallengesDataSource)
        return completeChallengesDataSource
    }

}