package com.aleks.aleksiev.codewars.utils.scheduler

import io.reactivex.Scheduler

interface SchedulersFacade {
    fun ioScheduler(): Scheduler
    fun computationScheduler(): Scheduler
    fun mainThreadScheduler(): Scheduler
}