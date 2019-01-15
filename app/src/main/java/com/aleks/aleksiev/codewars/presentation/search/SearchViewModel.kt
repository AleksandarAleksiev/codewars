package com.aleks.aleksiev.codewars.presentation.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.aleks.aleksiev.codewars.domain.model.Member
import com.aleks.aleksiev.codewars.domain.model.SortBy
import com.aleks.aleksiev.codewars.domain.usecase.search.MemberSearchUseCase
import com.aleks.aleksiev.codewars.presentation.RenderState
import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMember
import com.aleks.aleksiev.codewars.utils.Constants
import com.aleks.aleksiev.codewars.utils.Event
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import io.reactivex.Single
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val schedulersFacade: SchedulersFacade,
                                          private val memberSearchUseCase: MemberSearchUseCase
) : BaseViewModel() {

    private val _members = MutableLiveData<List<FoundMember>>()
    val members: LiveData<List<FoundMember>>
        get() = _members

    var sortBy: SortBy = SortBy.Date
        set(value) {
            if (value != field) {
                field = value
                searchHistory()
            }
        }

    fun findMember(memberName: String) {
        renderState(Event(RenderState(true)))
        add(Single.fromCallable { memberSearchUseCase.findMember(memberName) }
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({
                renderState(Event(RenderState(false)))
            }, { error ->
                renderState(Event(RenderState(false, error.message)))
            }))
    }

    fun searchHistory() {
        dispose()
        add(memberSearchUseCase.observeMemberSearch(Constants.MAX_SEARCHED_ITEMS_TO_SHOW, sortBy)
            .map { members ->
                members.map { toFoundMember(it) }
            }
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({ members ->
                _members.value = members
            }, { error ->
                renderState(Event(RenderState(false, error.message)))
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