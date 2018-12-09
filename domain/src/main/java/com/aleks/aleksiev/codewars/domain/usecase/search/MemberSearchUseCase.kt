package com.aleks.aleksiev.codewars.domain.usecase.search

import com.aleks.aleksiev.codewars.domain.datamodel.Member
import io.reactivex.Flowable

interface MemberSearchUseCase {

    fun findMember(memberName: String): Member
    fun observeMemberSearch(): Flowable<List<Member>>
}