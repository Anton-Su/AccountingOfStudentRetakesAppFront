package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.model.CommentDto
import com.example.accountingofstudentretakesapp.domain.model.CreateCommentRequestDto
import com.example.accountingofstudentretakesapp.domain.model.StudentDebtDto
import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRankDto
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

class StudentRepositoryImpl : StudentRepository {
    override suspend fun findDebtsByStudentId(studentId: Long): List<StudentDebtDto> {
        return KtorClient.getStudentDebts(studentId)
    }

    override suspend fun enrollToRetake(studentId: Long, debtId: Long, retakeId: Long): Boolean {
        return KtorClient.enrollToRetake(studentId, debtId, retakeId)
    }

    override suspend fun cancelRetakeEnrollment(studentId: Long, debtId: Long, retakeId: Long): Boolean {
        return KtorClient.cancelRetakeEnrollment(studentId, debtId, retakeId)
    }

    override suspend fun createComment(studentId: Long, request: CreateCommentRequestDto): CommentDto {
        return KtorClient.createComment(studentId, request)
    }

    override suspend fun getStudentDebtRank(studentId: Long): StudentDebtRankDto {
        return KtorClient.getStudentDebtRank(studentId)
    }
}

