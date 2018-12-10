package com.aleks.aleksiev.codewars.presentation.challenges

import com.aleks.aleksiev.codewars.databinding.LayoutCompletedChallengeBinding
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeModel
import com.aleks.aleksiev.codewars.presentation.common.BaseViewHolder

class ChallengeViewHolder(
    layoutCompletedChallengeBinding: LayoutCompletedChallengeBinding
) : BaseViewHolder<ChallengeModel, LayoutCompletedChallengeBinding>(layoutCompletedChallengeBinding) {

    override fun bind(item: ChallengeModel?) {
        binding.challenge = item
        binding.executePendingBindings()
    }
}