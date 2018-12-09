package com.aleks.aleksiev.codewars.model.repository

import com.aleks.aleksiev.codewars.model.entities.MemberSearch
import io.reactivex.Flowable

interface MemberSearchRepository : Repository {
    fun findMember(memberName: String): MemberSearch
    fun saveMemberSearch(memberSearch: MemberSearch): Long
    fun observeMemberSearch(): Flowable<List<MemberSearch>>
}