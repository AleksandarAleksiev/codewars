package com.aleks.aleksiev.codewars.model.repository

import com.aleks.aleksiev.codewars.model.entities.MemberSearch
import io.reactivex.Flowable

interface MemberSearchRepository : Repository {
    fun getMemberUserName(memberId: Long): String
    fun findMember(memberName: String): MemberSearch
    fun saveMemberSearch(memberSearch: MemberSearch): Long
    fun observeMemberSearchSortedByRank(numberOfSearches: Int): Flowable<List<MemberSearch>>
    fun observeMemberSearchSortedByDate(numberOfSearches: Int): Flowable<List<MemberSearch>>
}