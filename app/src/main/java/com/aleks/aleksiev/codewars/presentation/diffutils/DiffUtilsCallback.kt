package com.aleks.aleksiev.codewars.presentation.diffutils

import android.support.v7.util.DiffUtil

class DiffUtilsCallback<T : DiffUtilItemComparable<T>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem.areContentsTheSame(newItem)
}