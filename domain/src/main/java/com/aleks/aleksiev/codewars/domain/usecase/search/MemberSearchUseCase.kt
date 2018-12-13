package com.aleks.aleksiev.codewars.domain.usecase.search

import com.aleks.aleksiev.codewars.domain.model.Member
import com.aleks.aleksiev.codewars.domain.model.SortBy
import io.reactivex.Flowable

interface MemberSearchUseCase {

    fun findMember(memberName: String): Member
    fun observeMemberSearch(numberOfSearches: Int, sortBy: SortBy): Flowable<List<Member>>
}