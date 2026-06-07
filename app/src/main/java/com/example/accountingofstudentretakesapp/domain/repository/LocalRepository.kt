package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.model.Teacher

interface LocalRepository {
    suspend fun saveComments(comments: List<Comment>)
    suspend fun getAllComments(): List<Comment>
    suspend fun saveSubjects(subjects: List<Subject>)
    suspend fun getAllSubjects(): List<Subject>
    suspend fun saveTeachers(teachers: List<Teacher>)
    suspend fun getTeachersByDiscipline(discipline: String): List<Teacher>
}