package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toSubjectDomain
import com.example.accountingofstudentretakesapp.domain.repository.GuestRepository
import com.example.accountingofstudentretakesapp.domain.model.Subject

class GuestRepositoryImpl : GuestRepository {
    override suspend fun getSubjects(): List<Subject> {
        return KtorClient.getSubjects().map { it.toSubjectDomain() }
    }
}