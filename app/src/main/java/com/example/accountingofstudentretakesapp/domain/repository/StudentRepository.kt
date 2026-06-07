package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.StudentDebt
import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRank
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateCommentRequest

interface StudentRepository {
    suspend fun findDebtsByStudentId(studentId: Long): List<StudentDebt>
    suspend fun findAvailableRetakes(studentId: Long): List<Retake>
    suspend fun findEnrolledRetakes(studentId: Long): List<Retake>
    suspend fun enrollToRetake(studentId: Long, debtId: Long, retakeId: Long): Boolean
    suspend fun cancelRetakeEnrollment(studentId: Long, debtId: Long, retakeId: Long): Boolean
    suspend fun createComment(studentId: Long, request: CreateCommentRequest): Comment
    suspend fun getStudentDebtRank(studentId: Long): StudentDebtRank
}

