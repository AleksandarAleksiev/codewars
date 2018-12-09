package com.aleks.aleksiev.codewars.presentation.search.foundmembers

import com.aleks.aleksiev.codewars.databinding.LayoutSearchedMemberBinding
import com.aleks.aleksiev.codewars.presentation.common.BaseViewHolder

class FoundMemberViewHolder(
    layoutSearchedMemberBinding: LayoutSearchedMemberBinding
) : BaseViewHolder<FoundMember, LayoutSearchedMemberBinding>(layoutSearchedMemberBinding) {

    override fun bind(item: FoundMember?) {
        binding.member = item
        binding.executePendingBindings()
    }

}