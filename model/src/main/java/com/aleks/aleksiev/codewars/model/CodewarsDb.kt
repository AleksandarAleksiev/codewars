package com.aleks.aleksiev.codewars.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aleks.aleksiev.codewars.model.converters.AuthoredChallengesConverter
import com.aleks.aleksiev.codewars.model.converters.CompletedChallengesConverter
import com.aleks.aleksiev.codewars.model.converters.DateConverter
import com.aleks.aleksiev.codewars.model.entities.AuthoredChallengeEntity
import com.aleks.aleksiev.codewars.model.entities.CompletedChallengeEntity
import com.aleks.aleksiev.codewars.model.entities.MemberSearch

@Database(entities = [MemberSearch::class, CompletedChallengeEntity::class, AuthoredChallengeEntity::class], version = 1)
@TypeConverters(value = [DateConverter::class, CompletedChallengesConverter::class, AuthoredChallengesConverter::class])
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