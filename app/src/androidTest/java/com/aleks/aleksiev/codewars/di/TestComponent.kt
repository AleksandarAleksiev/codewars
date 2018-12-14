package com.aleks.aleksiev.codewars.di

import com.aleks.aleksiev.codewars.common.BaseTest
import com.aleks.aleksiev.codewars.common.TestApplication
import com.aleks.aleksiev.codewars.di.component.AppComponent
import com.aleks.aleksiev.codewars.domain.di.DomainModule
import com.aleks.aleksiev.codewars.model.di.ModelTestModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    TestAppModule::class,
    BuilderModule::class,
    ModelTestModule::class,
    NetworkModule::class,
    DomainModule::class,
    TestApiModule::class])
interface TestComponent : AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(codewarsApp: TestApplication): Builder
        fun model(modelModule: ModelTestModule): Builder
        fun build(): TestComponent
    }

    fun inject(codewarsApp: TestApplication)
    fun injectBase(baseTest: BaseTest)
}