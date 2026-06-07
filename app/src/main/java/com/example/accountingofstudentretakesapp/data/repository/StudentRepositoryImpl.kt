package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toCommentDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toRetakeDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toStudentDebtDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toStudentDebtRankDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDto.toСreateCommentRequestDto
import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.StudentDebt
import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRank
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateCommentRequest
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository


class StudentRepositoryImpl : StudentRepository {
    override suspend fun findDebtsByStudentId(studentId: Long): List<StudentDebt> {
        return KtorClient.getStudentDebts(studentId).map { it.toStudentDebtDomain() }
    }

    override suspend fun findAvailableRetakes(studentId: Long): List<Retake> {
        return KtorClient.getAvailableRetakes(studentId).map { it.toRetakeDomain() }
    }

    override suspend fun findEnrolledRetakes(studentId: Long): List<Retake> {
        return KtorClient.getEnrolledRetakes(studentId).map { it.toRetakeDomain() }
    }

    override suspend fun enrollToRetake(studentId: Long, debtId: Long, retakeId: Long): Boolean {
        return KtorClient.enrollToRetake(studentId, debtId, retakeId)
    }

    override suspend fun cancelRetakeEnrollment(studentId: Long, debtId: Long, retakeId: Long): Boolean {
        return KtorClient.cancelRetakeEnrollment(studentId, debtId, retakeId)
    }

    override suspend fun createComment(studentId: Long, request: CreateCommentRequest): Comment {
        return KtorClient.createComment(studentId, request.toСreateCommentRequestDto()).toCommentDomain()
    }

    override suspend fun getStudentDebtRank(studentId: Long): StudentDebtRank {
        return KtorClient.getStudentDebtRank(studentId).toStudentDebtRankDomain()
    }
}

