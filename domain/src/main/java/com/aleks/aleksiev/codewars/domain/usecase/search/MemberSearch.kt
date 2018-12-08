package com.aleks.aleksiev.codewars.domain.usecase.search

import com.aleks.aleksiev.codewars.domain.datamodel.Member
import com.aleks.aleksiev.codewars.model.repository.MemberSearchRepository
import javax.inject.Inject

internal class MemberSearch @Inject constructor(
    private val memberSearchRepository: MemberSearchRepository
) : MemberSearchUseCase {
    override fun findMember(memberName: String): Member {
        val memberEntity = memberSearchRepository.findMember(memberName = memberName)

        return Member("")
    }
}