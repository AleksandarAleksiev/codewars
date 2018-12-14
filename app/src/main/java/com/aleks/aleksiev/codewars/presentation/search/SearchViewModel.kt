package com.aleks.aleksiev.codewars.presentation.search

import com.aleks.aleksiev.codewars.domain.model.Member
import com.aleks.aleksiev.codewars.domain.model.SortBy
import com.aleks.aleksiev.codewars.domain.usecase.search.MemberSearchUseCase
import com.aleks.aleksiev.codewars.presentation.RenderState
import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMember
import com.aleks.aleksiev.codewars.utils.Constants
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import io.reactivex.Single
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val schedulersFacade: SchedulersFacade,
                                          private val memberSearchUseCase: MemberSearchUseCase,
                                          private val searchHistoryUpdateListener: SearchHistoryUpdateListener
) : BaseViewModel() {

    var sortBy: SortBy = SortBy.Date
        set(value) {
            if (value != field) {
                field = value
                searchHistory()
            }
        }

    fun findMember(memberName: String) {
        renderState(RenderState(true))
        add(Single.fromCallable { memberSearchUseCase.findMember(memberName) }
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({
                renderState(RenderState(false))
            }, { error ->
                renderState(RenderState(false, error.message))
            }))
    }

    fun searchHistory() {
        dispose()
        renderState(RenderState(true))
        add(memberSearchUseCase.observeMemberSearch(Constants.MAX_SEARCHED_ITEMS_TO_SHOW, sortBy)
            .map { members ->
                members.map { toFoundMember(it) }
            }
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({members ->
                searchHistoryUpdateListener.searchHistoryUpdated(members)
                renderState(RenderState(false))
            }, {error ->
                renderState(RenderState(false, error.message))
            }))
    }

    private fun toFoundMember(member: Member): FoundMember {
        return FoundMember(
            memberId = member.id,
            userName = member.memberUserName,
            name = member.memberName,
            rank = member.rank,
            bestLanguage = member.bestLanguage
        )
    }
}