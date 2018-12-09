package com.aleks.aleksiev.codewars.presentation.common

import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.ListAdapter
import com.aleks.aleksiev.codewars.presentation.diffutils.DiffUtilItemComparable
import com.aleks.aleksiev.codewars.presentation.diffutils.DiffUtilsCallback

abstract class BaseAdapter<T: DiffUtilItemComparable<T>, DB: ViewDataBinding> : ListAdapter<T,
    BaseViewHolder<T, DB>>(DiffUtilsCallback<T>()) {

    override fun onBindViewHolder(viwHolder: BaseViewHolder<T, DB>, position: Int) {
        viwHolder.bind(getItem(position))
    }
}