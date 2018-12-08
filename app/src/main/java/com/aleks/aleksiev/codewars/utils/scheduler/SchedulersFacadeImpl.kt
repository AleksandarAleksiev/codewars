package com.aleks.aleksiev.codewars.utils.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulersFacadeImpl @Inject constructor() : SchedulersFacade {

    override fun ioScheduler() = Schedulers.io()
    override fun computationScheduler() = Schedulers.computation()
    override fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}