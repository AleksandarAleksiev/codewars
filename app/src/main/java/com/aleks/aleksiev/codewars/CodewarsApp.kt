package com.aleks.aleksiev.codewars

import android.app.Activity
import android.app.Application
import com.aleks.aleksiev.codewars.di.component.AppComponent
import com.aleks.aleksiev.codewars.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CodewarsApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    private fun initDaggerComponent() {
        component = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        component.inject(this)
    }
}