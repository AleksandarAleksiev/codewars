package com.aleks.aleksiev.codewars.presentation.challenges.model

import com.aleks.aleksiev.codewars.presentation.diffutils.DiffUtilItemComparable

data class ChallengeModel (
    val challengeId: Long,
    val challengeName: String
) : DiffUtilItemComparable<ChallengeModel> {

    override fun areItemsTheSame(newItem: ChallengeModel): Boolean {
        return this.challengeId == newItem.challengeId
    }

    override fun areContentsTheSame(newItem: ChallengeModel): Boolean {
        return this == newItem
    }
}