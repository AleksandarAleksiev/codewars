package com.aleks.aleksiev.codewars.presentation.challenges.model

import com.aleks.aleksiev.codewars.presentation.diffutils.DiffUtilItemComparable

data class ChallengeModel (
    val challengeId: String,
    val challengesGroupId: Long,
    val challengeName: String,
    val completedAt: String,
    val challengeDescription: String
) : DiffUtilItemComparable<ChallengeModel> {

    override fun areItemsTheSame(newItem: ChallengeModel): Boolean {
        return this.challengesGroupId == newItem.challengesGroupId
    }

    override fun areContentsTheSame(newItem: ChallengeModel): Boolean {
        return this == newItem
    }
}