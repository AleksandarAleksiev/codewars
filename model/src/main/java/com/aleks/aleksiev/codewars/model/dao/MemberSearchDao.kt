package com.aleks.aleksiev.codewars.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aleks.aleksiev.codewars.model.entities.MemberSearch
import io.reactivex.Flowable

@Dao
interface MemberSearchDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(memberSearch: MemberSearch): Long

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun update(memberSearch: MemberSearch)

    @Delete
    fun delete(memberSearch: MemberSearch)

    @Query("select * from member_search order by searched_on_date desc limit :maxUsers")
    fun observeMemberSearchByDate(maxUsers: Int): Flowable<List<MemberSearch>>

    @Query("select * from member_search order by rank desc limit :maxUsers")
    fun observeMemberSearchByRank(maxUsers: Int): Flowable<List<MemberSearch>>

    @Query("select member_user_name from member_search where id = :userId")
    fun getUserName(userId: Long): String

    @Query("select * from member_search where member_user_name = :userName")
    fun getUser(userName: String): MemberSearch?
}