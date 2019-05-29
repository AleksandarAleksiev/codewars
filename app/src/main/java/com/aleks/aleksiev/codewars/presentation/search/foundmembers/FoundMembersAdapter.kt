package com.aleks.aleksiev.codewars.presentation.search.foundmembers

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aleks.aleksiev.codewars.databinding.LayoutSearchedMemberBinding
import com.aleks.aleksiev.codewars.presentation.common.BaseAdapter
import com.aleks.aleksiev.codewars.presentation.common.BaseViewHolder
import com.aleks.aleksiev.codewars.utils.ItemClicked
import javax.inject.Inject

class FoundMembersAdapter @Inject constructor(private val itemClicked: ItemClicked<FoundMember>) : BaseAdapter<FoundMember, LayoutSearchedMemberBinding>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FoundMember, LayoutSearchedMemberBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutSearchedMemberBinding.inflate(inflater, parent, false)
        return FoundMemberViewHolder(binding).apply {
            this.itemView.setOnClickListener {
                val position = this.adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    this@FoundMembersAdapter.itemClicked.onItemClicked(getItem(this.adapterPosition))
                }
            }
        }
    }
}