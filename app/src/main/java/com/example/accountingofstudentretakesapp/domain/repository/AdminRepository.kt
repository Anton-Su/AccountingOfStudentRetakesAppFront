package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.model.Teacher
import java.time.Instant

interface AdminRepository {
    suspend fun getTeachersByDiscipline(discipline: String): List<Teacher>
    suspend fun getSubjects(): List<Subject>
    suspend fun createRetake(request: CreateRetakeRequest): Retake
    suspend fun updateRetake(id: Long, request: CreateRetakeRequest): Retake
    suspend fun deleteRetake(id: Long)
    suspend fun getAllComments(): List<Comment>
    suspend fun getAllRetakes(): List<Retake>

}

