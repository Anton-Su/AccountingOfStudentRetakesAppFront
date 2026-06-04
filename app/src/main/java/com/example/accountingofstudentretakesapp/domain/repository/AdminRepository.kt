package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.CreateRetakeRequestDto
import com.example.accountingofstudentretakesapp.presentation.model.Comment
import com.example.accountingofstudentretakesapp.presentation.model.Retake
import com.example.accountingofstudentretakesapp.presentation.model.Subject
import com.example.accountingofstudentretakesapp.presentation.model.Teacher
import java.time.Instant

interface AdminRepository {
    suspend fun getTeachersByDiscipline(discipline: String): List<Teacher>
    suspend fun getSubjects(): List<Subject>
    suspend fun createRetake(startAt: Instant, endAt: Instant, teacherIds: List<Long>, subjectId: Long, type: String, place: String, admission: String?): Retake
    suspend fun updateRetake(id: Long, startAt: Instant, endAt: Instant, teacherIds: List<Long>, subjectId: Long, type: String, place: String, admission: String?): Retake
    suspend fun deleteRetake(id: Long)
    suspend fun getAllComments(): List<Comment>
    suspend fun getAllRetakes(): List<Retake>

}

