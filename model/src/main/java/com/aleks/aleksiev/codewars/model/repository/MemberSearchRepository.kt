package com.aleks.aleksiev.codewars.model.repository

import com.aleks.aleksiev.codewars.model.entities.MemberSearch

interface MemberSearchRepository : Repository {
    fun findMember(memberName: String): MemberSearch
}