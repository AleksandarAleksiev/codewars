package com.aleks.aleksiev.codewars.model.di

import android.arch.persistence.room.Room
import android.content.Context
import com.aleks.aleksiev.codewars.model.CodewarsDatabase
import com.aleks.aleksiev.codewars.model.CodewarsDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModelModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesCspDatabase(): CodewarsDatabase {
        return Room.databaseBuilder(context.applicationContext, CodewarsDb::class.java, "codewars_database")
            .build()
    }
}