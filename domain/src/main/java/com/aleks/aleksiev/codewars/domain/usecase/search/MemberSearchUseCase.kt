package com.aleks.aleksiev.codewars.domain.usecase.search

import com.aleks.aleksiev.codewars.domain.datamodel.Member

interface MemberSearchUseCase {

    fun findMember(memberName: String): Member
}