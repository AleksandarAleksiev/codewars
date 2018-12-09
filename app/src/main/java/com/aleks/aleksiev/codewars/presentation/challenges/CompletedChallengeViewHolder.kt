package com.aleks.aleksiev.codewars.presentation.challenges

import com.aleks.aleksiev.codewars.databinding.LayoutCompletedChallengeBinding
import com.aleks.aleksiev.codewars.presentation.challenges.model.CompletedChallengeModel
import com.aleks.aleksiev.codewars.presentation.common.BaseViewHolder

class CompletedChallengeViewHolder(
    layoutCompletedChallengeBinding: LayoutCompletedChallengeBinding
) : BaseViewHolder<CompletedChallengeModel, LayoutCompletedChallengeBinding>(layoutCompletedChallengeBinding) {

    override fun bind(item: CompletedChallengeModel?) {
        binding.challenge = item
        binding.executePendingBindings()
    }
}