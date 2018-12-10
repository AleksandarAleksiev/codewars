package com.aleks.aleksiev.codewars.presentation.challenges.model

import com.aleks.aleksiev.codewars.presentation.diffutils.DiffUtilItemComparable
import java.util.Date

data class ChallengeModel (
    val challengeId: Long,
    val challengeName: String,
    val challengeCompletedAt: Date
) : DiffUtilItemComparable<ChallengeModel> {

    override fun areItemsTheSame(newItem: ChallengeModel): Boolean {
        return this.challengeId == newItem.challengeId
    }

    override fun areContentsTheSame(newItem: ChallengeModel): Boolean {
        return this == newItem
    }
}