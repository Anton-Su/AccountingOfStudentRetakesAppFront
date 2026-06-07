package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.model.CreateCommentRequestDto
import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toCommentDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toRetakeDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toStudentDebtDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toStudentDebtRankDomain
import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.StudentDebt
import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRank
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

    override suspend fun createComment(studentId: Long, request: CreateCommentRequestDto): Comment {
        return KtorClient.createComment(studentId, request).toCommentDomain()
    }

    override suspend fun getStudentDebtRank(studentId: Long): StudentDebtRank {
        return KtorClient.getStudentDebtRank(studentId).toStudentDebtRankDomain()
    }
}

