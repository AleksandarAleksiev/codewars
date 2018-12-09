package com.aleks.aleksiev.codewars.presentation.search

import android.util.Log
import com.aleks.aleksiev.codewars.domain.datamodel.Member
import com.aleks.aleksiev.codewars.domain.usecase.search.MemberSearchUseCase
import com.aleks.aleksiev.codewars.presentation.common.BaseViewModel
import com.aleks.aleksiev.codewars.presentation.common.navigator.Navigator
import com.aleks.aleksiev.codewars.presentation.search.foundmembers.FoundMember
import com.aleks.aleksiev.codewars.utils.scheduler.SchedulersFacade
import io.reactivex.Single
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val navigator: Navigator,
                                          private val schedulersFacade: SchedulersFacade,
                                          private val memberSearchUseCase: MemberSearchUseCase,
                                          private val searchHistoryUpdateListener: SearchHistoryUpdateListener
) : BaseViewModel(), Navigator by navigator {

    fun findMember(memberName: String?) {
        memberName?.let {
            add(Single.fromCallable { memberSearchUseCase.findMember(it) }
                .subscribeOn(schedulersFacade.ioScheduler())
                .observeOn(schedulersFacade.mainThreadScheduler())
                .subscribe({member ->
                    Log.d("", "")
                }, { error ->
                    Log.d("", "")
                }))
        }
    }

    fun searchHistory() {
        add(memberSearchUseCase.observeMemberSearch()
            .map { members ->
                members.map { toFoundMember(it) }
            }
            .subscribeOn(schedulersFacade.ioScheduler())
            .observeOn(schedulersFacade.mainThreadScheduler())
            .subscribe({members ->
                searchHistoryUpdateListener.searchHistoryUpdated(members)
            }, {error ->
                Log.d("", "")
            }))
    }

    private fun toFoundMember(member: Member): FoundMember {
        val memberName = if (member.memberName.isBlank()) {
            member.memberUserName
        } else {
            member.memberName
        }
        return FoundMember(
            name = memberName,
            rank = 0,
            bestLanguage = ""
        )
    }
}