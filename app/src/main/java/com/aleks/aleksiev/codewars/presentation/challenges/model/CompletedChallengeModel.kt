package com.aleks.aleksiev.codewars.presentation.challenges.model

import com.aleks.aleksiev.codewars.presentation.diffutils.DiffUtilItemComparable
import java.util.Date

data class CompletedChallengeModel (
    val challengeId: Long,
    val challengeName: String,
    val challengeCompletedAt: Date
) : DiffUtilItemComparable<CompletedChallengeModel> {

    override fun areItemsTheSame(newItem: CompletedChallengeModel): Boolean {
        return this.challengeId == newItem.challengeId
    }

    override fun areContentsTheSame(newItem: CompletedChallengeModel): Boolean {
        return this == newItem
    }
}