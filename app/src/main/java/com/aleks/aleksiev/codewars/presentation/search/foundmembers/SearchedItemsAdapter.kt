package com.aleks.aleksiev.codewars.presentation.search.foundmembers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aleks.aleksiev.codewars.databinding.LayoutSearchedMemberBinding
import com.aleks.aleksiev.codewars.presentation.common.BaseAdapter
import com.aleks.aleksiev.codewars.presentation.common.BaseViewHolder
import javax.inject.Inject

class SearchedItemsAdapter @Inject constructor() : BaseAdapter<FoundMember, LayoutSearchedMemberBinding>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FoundMember, LayoutSearchedMemberBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutSearchedMemberBinding.inflate(inflater, parent, false)
        return FoundMemberViewHolder(binding)
    }
}