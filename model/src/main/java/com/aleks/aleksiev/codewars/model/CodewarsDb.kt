package com.aleks.aleksiev.codewars.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.aleks.aleksiev.codewars.model.converters.ChallengesConverter
import com.aleks.aleksiev.codewars.model.converters.DateConverter
import com.aleks.aleksiev.codewars.model.entities.CompletedChallenge
import com.aleks.aleksiev.codewars.model.entities.MemberSearch

@Database(entities = [MemberSearch::class, CompletedChallenge::class], version = 1)
@TypeConverters(value = [DateConverter::class, ChallengesConverter::class])
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