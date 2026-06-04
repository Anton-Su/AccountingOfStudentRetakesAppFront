package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.CreateCommentRequestDto
import com.example.accountingofstudentretakesapp.presentation.model.Comment
import com.example.accountingofstudentretakesapp.presentation.model.Retake
import com.example.accountingofstudentretakesapp.presentation.model.StudentDebt
import com.example.accountingofstudentretakesapp.presentation.model.StudentDebtRank

interface StudentRepository {
    suspend fun findDebtsByStudentId(studentId: Long): List<StudentDebt>
    suspend fun findAvailableRetakes(studentId: Long): List<Retake>
    suspend fun findEnrolledRetakes(studentId: Long): List<Retake>
    suspend fun enrollToRetake(studentId: Long, debtId: Long, retakeId: Long): Boolean
    suspend fun cancelRetakeEnrollment(studentId: Long, debtId: Long, retakeId: Long): Boolean
    suspend fun createComment(studentId: Long, request: CreateCommentRequestDto): Comment
    suspend fun getStudentDebtRank(studentId: Long): StudentDebtRank
}

