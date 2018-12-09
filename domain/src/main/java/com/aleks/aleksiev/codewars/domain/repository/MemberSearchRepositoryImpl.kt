package com.aleks.aleksiev.codewars.domain.repository

import com.aleks.aleksiev.codewars.domain.rest.UserController
import com.aleks.aleksiev.codewars.domain.rest.response.ApiEmptyResponse
import com.aleks.aleksiev.codewars.domain.rest.response.ApiResponse
import com.aleks.aleksiev.codewars.domain.rest.response.ApiSuccessResponse
import com.aleks.aleksiev.codewars.domain.rest.response.MemberSearchResponse
import com.aleks.aleksiev.codewars.model.CodewarsDatabase
import com.aleks.aleksiev.codewars.model.entities.MemberSearch
import com.aleks.aleksiev.codewars.model.repository.MemberSearchRepository
import com.aleks.aleksiev.codewars.model.repository.Repository
import io.reactivex.Flowable
import javax.inject.Inject

internal class MemberSearchRepositoryImpl @Inject constructor(
    private val repository: Repository,
    private val database: CodewarsDatabase,
    private val userController: UserController
) : MemberSearchRepository, Repository by repository {

    override fun findMember(memberName: String): MemberSearch {
        val response = userController.findUser(memberName)
        return onApiResponse(response)
    }

    override fun saveMemberSearch(memberSearch: MemberSearch): Long {
        return database.memberSearchDao().insert(memberSearch)
    }

    override fun observeMemberSearch(): Flowable<List<MemberSearch>> = database.memberSearchDao().observeMemberSearch()

    private fun onApiResponse(response: ApiResponse<MemberSearchResponse>): MemberSearch {
        return when (response) {
            is ApiSuccessResponse -> cacheResponse(response.body)
            is ApiEmptyResponse -> throw Exception()
            else -> throw Exception()
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
            leaderBoardPosition = memberSearchResponse.leaderBoardPosition
        )
    }
}