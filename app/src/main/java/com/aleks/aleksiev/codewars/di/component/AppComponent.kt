package com.aleks.aleksiev.codewars.di.component

import com.aleks.aleksiev.codewars.CodewarsApp
import com.aleks.aleksiev.codewars.di.AppModule
import com.aleks.aleksiev.codewars.di.BuilderModule
import com.aleks.aleksiev.codewars.di.NetworkModule
import com.aleks.aleksiev.codewars.domain.di.DomainModule
import com.aleks.aleksiev.codewars.model.di.ModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    AppModule::class,
    BuilderModule::class,
    ModelModule::class,
    DomainModule::class,
    NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(codewarsApp: CodewarsApp): Builder
        fun model(modelModule: ModelModule): Builder
        fun build(): AppComponent
    }

    fun inject(codewarsApp: CodewarsApp)
}