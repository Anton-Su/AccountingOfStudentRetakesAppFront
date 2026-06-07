package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toCommentDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toRetakeDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toSubjectDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toTeacherDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toСreateRetakeDto
import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

class AdminRepositoryImpl : AdminRepository {
    override suspend fun getTeachersByDiscipline(discipline: String): List<Teacher> {
        return KtorClient.getTeachersByDiscipline(discipline).map { it.toTeacherDomain() }
    }

    override suspend fun getSubjects(): List<Subject> {
        return KtorClient.getSubjects().map { it.toSubjectDomain() }
    }

    override suspend fun createRetake(request: CreateRetakeRequest): Retake {
        return KtorClient.createRetake(request.toСreateRetakeDto()).toRetakeDomain()
    }

    override suspend fun updateRetake(id: Long, request: CreateRetakeRequest): Retake {
        return KtorClient.updateRetake(id, request.toСreateRetakeDto()).toRetakeDomain()
    }

    override suspend fun deleteRetake(id: Long) {
        KtorClient.deleteRetake(id)
    }

    override suspend fun getAllComments(): List<Comment> {
        return KtorClient.getAllComments().map { it.toCommentDomain() }
    }

    override suspend fun getAllRetakes(): List<Retake> {
        return KtorClient.getAllRetakes().map { it.toRetakeDomain() }
    }
}


