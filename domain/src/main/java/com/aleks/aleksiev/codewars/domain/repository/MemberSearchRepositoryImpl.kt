package com.aleks.aleksiev.codewars.domain.repository

import com.aleks.aleksiev.codewars.domain.rest.UserController
import com.aleks.aleksiev.codewars.model.entities.MemberSearch
import com.aleks.aleksiev.codewars.model.repository.MemberSearchRepository
import com.aleks.aleksiev.codewars.model.repository.Repository
import javax.inject.Inject

internal class MemberSearchRepositoryImpl @Inject constructor(
    private val repository: Repository,
    private val userController: UserController
) : MemberSearchRepository, Repository by repository {

    override fun findMember(memberName: String): MemberSearch {
        val userResponse = userController.findUser(memberName).execute()

        return MemberSearch("")
    }
}