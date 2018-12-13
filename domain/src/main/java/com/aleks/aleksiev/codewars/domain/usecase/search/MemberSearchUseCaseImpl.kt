package com.aleks.aleksiev.codewars.domain.usecase.search

import com.aleks.aleksiev.codewars.domain.model.Member
import com.aleks.aleksiev.codewars.domain.model.SortBy
import com.aleks.aleksiev.codewars.model.entities.MemberSearch
import com.aleks.aleksiev.codewars.model.repository.MemberSearchRepository
import io.reactivex.Flowable
import javax.inject.Inject

class MemberSearchUseCaseImpl @Inject constructor(
    private val memberSearchRepository: MemberSearchRepository
) : MemberSearchUseCase {
    override fun findMember(memberName: String): Member {
        val memberEntity = memberSearchRepository.findMember(memberName = memberName)
        return toMember(memberEntity)
    }

    override fun observeMemberSearch(numberOfSearches: Int, sortBy: SortBy): Flowable<List<Member>> {
        val flowable = when (sortBy) {
            SortBy.Date -> memberSearchRepository.observeMemberSearchSortedByDate(numberOfSearches)
            SortBy.Rank -> memberSearchRepository.observeMemberSearchSortedByRank(numberOfSearches)
        }
        return flowable.flatMap { list ->
            val membersList = list.map { toMember(it) }
            Flowable.fromCallable { membersList }
        }
    }

    private fun toMember(memberSearch: MemberSearch): Member {
        return Member(
            id = memberSearch.id,
            clan = memberSearch.clan,
            rank = memberSearch.rank,
            honor = memberSearch.honor,
            memberName = memberSearch.memberName,
            bestLanguage = memberSearch.bestLanguage,
            memberUserName = memberSearch.memberUserName,
            leaderBoardPosition = memberSearch.leaderBoardPosition
        )
    }
}