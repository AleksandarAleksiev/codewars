package com.aleks.aleksiev.codewars

import android.app.Activity
import android.app.Application
import com.aleks.aleksiev.codewars.di.component.AppComponent
import com.aleks.aleksiev.codewars.di.component.DaggerAppComponent
import com.aleks.aleksiev.codewars.model.di.ModelModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class CodewarsApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    protected open fun initDaggerComponent() {
        component = DaggerAppComponent
            .builder()
            .application(this)
            .model(ModelModule(this))
            .build()

        component.inject(this)
    }
}