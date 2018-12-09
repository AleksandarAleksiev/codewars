package com.aleks.aleksiev.codewars.presentation.search.foundmembers

import com.aleks.aleksiev.codewars.presentation.diffutils.DiffUtilItemComparable

data class FoundMember(val name: String,
                       val rank: Int,
                       val bestLanguage: String) : DiffUtilItemComparable<FoundMember> {

    override fun areItemsTheSame(newItem: FoundMember): Boolean {
        return this === newItem
    }

    override fun areContentsTheSame(newItem: FoundMember): Boolean {
        return this == newItem
    }
}