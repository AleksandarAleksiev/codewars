package com.aleks.aleksiev.codewars.presentation.diffutils

interface DiffUtilItemComparable<T : Any> {
    fun areItemsTheSame(newItem: T): Boolean
    fun areContentsTheSame(newItem: T): Boolean
}