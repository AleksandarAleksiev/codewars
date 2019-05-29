package com.aleks.aleksiev.codewars.presentation.challenges.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.aleks.aleksiev.codewars.presentation.challenges.ChallengesViewModel
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeModel
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade

class CompleteChallengesDataSourceFactory (
    private val schedulersFacade: SchedulersFacade,
    private val challengesViewModel: ChallengesViewModel
): DataSource.Factory<Int, ChallengeModel>() {

    val completeChallengesDataSourceLiveData = MutableLiveData<CompleteChallengesDataSource>()

    override fun create(): DataSource<Int, ChallengeModel> {
        val completeChallengesDataSource = CompleteChallengesDataSource(schedulersFacade, challengesViewModel)
        completeChallengesDataSourceLiveData.postValue(completeChallengesDataSource)
        return completeChallengesDataSource
    }

}