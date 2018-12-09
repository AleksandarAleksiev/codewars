package com.aleks.aleksiev.codewars.presentation.challenges

import com.aleks.aleksiev.codewars.databinding.LayoutNetworkStateBinding
import com.aleks.aleksiev.codewars.presentation.challenges.model.NetworkStateModel
import com.aleks.aleksiev.codewars.presentation.common.BaseViewHolder

class NetworkStateViewHolder(
    layoutNetworkStateBinding: LayoutNetworkStateBinding
) : BaseViewHolder<NetworkStateModel, LayoutNetworkStateBinding>(layoutNetworkStateBinding) {
    override fun bind(item: NetworkStateModel?) {
        binding.networkState = item
        binding.executePendingBindings()
    }
}