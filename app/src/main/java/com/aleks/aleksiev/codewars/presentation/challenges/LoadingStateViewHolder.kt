package com.aleks.aleksiev.codewars.presentation.challenges

import com.aleks.aleksiev.codewars.databinding.LayoutNetworkStateBinding
import com.aleks.aleksiev.codewars.presentation.challenges.model.LoadingStateModel
import com.aleks.aleksiev.codewars.presentation.common.BaseViewHolder

class LoadingStateViewHolder(
    layoutNetworkStateBinding: LayoutNetworkStateBinding
) : BaseViewHolder<LoadingStateModel, LayoutNetworkStateBinding>(layoutNetworkStateBinding) {
    override fun bind(item: LoadingStateModel?) {
        binding.networkState = item
        binding.executePendingBindings()
    }
}