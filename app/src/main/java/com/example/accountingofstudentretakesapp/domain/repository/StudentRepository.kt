package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.CommentDto
import com.example.accountingofstudentretakesapp.domain.model.CreateCommentRequestDto
import com.example.accountingofstudentretakesapp.domain.model.RetakeDetailDto
import com.example.accountingofstudentretakesapp.domain.model.StudentDebtDto
import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRankDto

interface StudentRepository {
    suspend fun findDebtsByStudentId(studentId: Long): List<StudentDebtDto>
    suspend fun findAvailableRetakes(studentId: Long): List<RetakeDetailDto>
    suspend fun findEnrolledRetakes(studentId: Long): List<RetakeDetailDto>
    suspend fun enrollToRetake(studentId: Long, debtId: Long, retakeId: Long): Boolean
    suspend fun cancelRetakeEnrollment(studentId: Long, debtId: Long, retakeId: Long): Boolean
    suspend fun createComment(studentId: Long, request: CreateCommentRequestDto): CommentDto
    suspend fun getStudentDebtRank(studentId: Long): StudentDebtRankDto
}

