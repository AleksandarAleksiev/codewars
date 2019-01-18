package com.aleks.aleksiev.codewars.domain.repository.search

import com.aleks.aleksiev.codewars.domain.Constants
import com.aleks.aleksiev.codewars.domain.model.ApiErrorException
import com.aleks.aleksiev.codewars.domain.model.EmptyResponseException
import com.aleks.aleksiev.codewars.domain.rest.UserController
import com.aleks.aleksiev.codewars.domain.rest.response.ApiErrorResponse
import com.aleks.aleksiev.codewars.domain.rest.response.ApiResponse
import com.aleks.aleksiev.codewars.domain.rest.response.ApiSuccessResponse
import com.aleks.aleksiev.codewars.domain.rest.response.MemberSearchResponse
import com.aleks.aleksiev.codewars.model.CodewarsDatabase
import com.aleks.aleksiev.codewars.model.entities.MemberSearch
import com.aleks.aleksiev.codewars.model.repository.MemberSearchRepository
import com.aleks.aleksiev.codewars.model.repository.Repository
import io.reactivex.Flowable
import java.util.Date
import javax.inject.Inject

class MemberSearchRepositoryImpl @Inject constructor(
    private val repository: Repository,
    private val database: CodewarsDatabase,
    private val userController: UserController
) : MemberSearchRepository, Repository by repository {

    override fun getMemberUserName(memberId: Long): String {
        return database.memberSearchDao().getUserName(memberId)
    }

    override fun findMember(memberName: String): MemberSearch {
        return fetchFromDB(memberName)?.also {
            it.searchedDate = Date()
            database.memberSearchDao().update(memberSearch = it)
        } ?: fetchNetwork(memberName)
    }

    override fun saveMemberSearch(memberSearch: MemberSearch): Long {
        return database.memberSearchDao().insert(memberSearch)
    }

    override fun observeMemberSearchSortedByDate(numberOfSearches: Int): Flowable<List<MemberSearch>>{
        return database.memberSearchDao().observeMemberSearchByDate(numberOfSearches)
    }

    override fun observeMemberSearchSortedByRank(numberOfSearches: Int): Flowable<List<MemberSearch>> {
        return database.memberSearchDao().observeMemberSearchByRank(numberOfSearches)
    }

    private fun fetchFromDB(memberName: String): MemberSearch? {
        return database.memberSearchDao().getUser(userName = memberName)
    }

    private fun fetchNetwork(memberName: String): MemberSearch {
        val response = userController.findUser(memberName)
        return onApiResponse(response)
    }

    private fun onApiResponse(response: ApiResponse<MemberSearchResponse>): MemberSearch {
        return when (response) {
            is ApiSuccessResponse -> cacheResponse(response.body)
            is ApiErrorResponse -> throw ApiErrorException(response.errorMessage)
            else -> throw EmptyResponseException()
        }
    }

    private fun cacheResponse(responseBody: MemberSearchResponse): MemberSearch {
        val memberSearch = toMemberSearch(responseBody)
        memberSearch.id = saveMemberSearch(memberSearch)
        return memberSearch
    }

    private fun toMemberSearch(memberSearchResponse: MemberSearchResponse): MemberSearch {
        return MemberSearch(
            memberUserName = memberSearchResponse.userName,
            memberName = memberSearchResponse.name,
            honor = memberSearchResponse.honor,
            clan = memberSearchResponse.clan,
            rank = memberSearchResponse.ranks.rank.rank,
            bestLanguage = memberSearchResponse.ranks.languages.languages.maxBy { it.rank }?.languageName ?: Constants.EMPTY_STRING,
            leaderBoardPosition = memberSearchResponse.leaderBoardPosition,
            searchedDate = Date()
        )
    }
}