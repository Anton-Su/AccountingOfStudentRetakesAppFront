package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toCommentDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toRetakeDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toTeacherDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDto.toRedactRetakeDto
import com.example.accountingofstudentretakesapp.domain.mapper.toDto.toСreateRetakeRequestDto
import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.requests.RedactRetakeRequest
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

class AdminRepositoryImpl : AdminRepository {
    override suspend fun getTeachersByDiscipline(discipline: String): List<Teacher> {
        return KtorClient.getTeachersByDiscipline(discipline).map { it.toTeacherDomain() }
    }

    override suspend fun createRetake(request: CreateRetakeRequest): Retake {
        return KtorClient.createRetake(request.toСreateRetakeRequestDto()).toRetakeDomain()
    }

    override suspend fun updateRetake(request: RedactRetakeRequest): Retake {
        return KtorClient.updateRetake(request.id, request.toRedactRetakeDto()).toRetakeDomain()
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


