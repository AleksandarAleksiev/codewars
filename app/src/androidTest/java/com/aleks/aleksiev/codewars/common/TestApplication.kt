package com.aleks.aleksiev.codewars.common

import com.aleks.aleksiev.codewars.CodewarsApp
import com.aleks.aleksiev.codewars.di.DaggerTestComponent
import com.aleks.aleksiev.codewars.model.di.ModelTestModule

class TestApplication : CodewarsApp() {

    override fun initDaggerComponent() {
        component = DaggerTestComponent
            .builder()
            .application(this)
            .model(ModelTestModule(this))
            .build()

        component.inject(this)
    }
}