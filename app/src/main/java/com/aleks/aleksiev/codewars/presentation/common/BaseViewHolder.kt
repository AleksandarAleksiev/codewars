package com.aleks.aleksiev.codewars.presentation.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.aleks.aleksiev.codewars.presentation.diffutils.DiffUtilItemComparable

abstract class BaseViewHolder<T: DiffUtilItemComparable<T>, VB : ViewDataBinding>(protected val binding: VB) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: T?)
}