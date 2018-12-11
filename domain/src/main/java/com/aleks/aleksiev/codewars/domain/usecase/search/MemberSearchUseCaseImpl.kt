package com.aleks.aleksiev.codewars.domain.usecase.search

import com.aleks.aleksiev.codewars.domain.model.Member
import com.aleks.aleksiev.codewars.model.entities.MemberSearch
import com.aleks.aleksiev.codewars.model.repository.MemberSearchRepository
import io.reactivex.Flowable
import javax.inject.Inject

internal class MemberSearchUseCaseImpl @Inject constructor(
    private val memberSearchRepository: MemberSearchRepository
) : MemberSearchUseCase {
    override fun findMember(memberName: String): Member {
        val memberEntity = memberSearchRepository.findMember(memberName = memberName)
        return toMember(memberEntity)
    }

    override fun observeMemberSearch(numberOfSearches: Int): Flowable<List<Member>> {
        return memberSearchRepository
            .observeMemberSearch(numberOfSearches)
            .flatMap { list ->
                val membersList = list.map { toMember(it) }
                Flowable.fromCallable { membersList }
            }
    }

    private fun toMember(memberSearch: MemberSearch): Member {
        return Member(
            memberUserName = memberSearch.memberUserName,
            memberName = memberSearch.memberName,
            honor = memberSearch.honor,
            clan = memberSearch.clan,
            leaderBoardPosition = memberSearch.leaderBoardPosition,
            id = memberSearch.id
        )
    }
}