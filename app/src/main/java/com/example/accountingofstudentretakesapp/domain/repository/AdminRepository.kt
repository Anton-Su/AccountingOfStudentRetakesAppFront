package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.requests.RedactRetakeRequest

interface AdminRepository {
    suspend fun getTeachersByDiscipline(discipline: String): List<Teacher>
    suspend fun createRetake(request: CreateRetakeRequest): Retake
    suspend fun updateRetake(request: RedactRetakeRequest): Retake
    suspend fun deleteRetake(id: Long)
    suspend fun getAllComments(): List<Comment>
    suspend fun getAllRetakes(): List<Retake>
}

