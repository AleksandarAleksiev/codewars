package com.aleks.aleksiev.codewars.domain.usecase.search

import com.aleks.aleksiev.codewars.domain.model.Member
import io.reactivex.Flowable

interface MemberSearchUseCase {

    fun findMember(memberName: String): Member
    fun observeMemberSearch(numberOfSearches: Int): Flowable<List<Member>>
}