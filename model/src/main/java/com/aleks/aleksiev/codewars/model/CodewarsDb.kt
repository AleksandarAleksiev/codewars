package com.aleks.aleksiev.codewars.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.aleks.aleksiev.codewars.model.converters.DateConverter
import com.aleks.aleksiev.codewars.model.entities.MemberSearch

@Database(entities = [MemberSearch::class], version = 3)
@TypeConverters(value = [DateConverter::class])
internal abstract class CodewarsDb : RoomDatabase(), CodewarsDatabase {

    override fun <T>executeInTransaction(transactionBlock: () -> T): T {
        return runInTransaction<T>{
            return@runInTransaction transactionBlock()
        }
    }

    override fun close() {
        super.close()
    }
}