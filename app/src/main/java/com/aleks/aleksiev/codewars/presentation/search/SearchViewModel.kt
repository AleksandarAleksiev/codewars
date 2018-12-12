package com.aleks.aleksiev.codewars.presentation.search

import com.aleks.aleksiev.codewars.domain.model.Member
import com.aleks.aleksiev.codewars.domain.usecase.search.MemberSearchUseCase
import com.aleks.aleksiev.codewars.presentation.RenderState
import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMember
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import io.reactivex.Single
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val schedulersFacade: SchedulersFacade,
                                          private val memberSearchUseCase: MemberSearchUseCase,
                                          private val searchHistoryUpdateListener: SearchHistoryUpdateListener
) : BaseViewModel() {

    fun findMember(memberName: String) {
        renderState(RenderState.LoadingState(true))
        add(Single.fromCallable { memberSearchUseCase.findMember(memberName) }
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({member ->
                renderState(RenderState.LoadingState(false))
            }, { error ->
                renderState(RenderState.LoadingState(false))
            }))
    }

    fun searchHistory(maxSearches: Int) {
        renderState(RenderState.LoadingState(false))
        add(memberSearchUseCase.observeMemberSearch(maxSearches)
            .map { members ->
                members.map { toFoundMember(it) }
            }
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({members ->
                searchHistoryUpdateListener.searchHistoryUpdated(members)
                renderState(RenderState.LoadingState(false))
            }, {error ->
                renderState(RenderState.LoadingState(false))
            }))
    }

    private fun toFoundMember(member: Member): FoundMember {
        return FoundMember(
            memberId = member.id,
            userName = member.memberUserName,
            name = member.memberName,
            rank = 0,
            bestLanguage = ""
        )
    }
}