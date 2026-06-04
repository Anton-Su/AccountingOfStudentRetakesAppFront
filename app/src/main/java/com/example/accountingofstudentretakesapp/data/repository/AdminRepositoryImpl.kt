package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.domain.model.CommentDto
import com.example.accountingofstudentretakesapp.domain.model.CreateRetakeRequestDto
import com.example.accountingofstudentretakesapp.domain.model.SubjectDto
import com.example.accountingofstudentretakesapp.domain.model.TeacherDto
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository
import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toCommentDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toRetakeDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toSubjectDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toTeacherDomain
import com.example.accountingofstudentretakesapp.domain.model.RetakeDto
import com.example.accountingofstudentretakesapp.presentation.model.Comment
import com.example.accountingofstudentretakesapp.presentation.model.Retake
import com.example.accountingofstudentretakesapp.presentation.model.Subject
import com.example.accountingofstudentretakesapp.presentation.model.Teacher

class AdminRepositoryImpl : AdminRepository {
    override suspend fun getTeachersByDiscipline(discipline: String): List<Teacher> {
        return KtorClient.getTeachersByDiscipline(discipline).map { it.toTeacherDomain() }
    }

    override suspend fun getSubjects(): List<Subject> {
        return KtorClient.getSubjects().map { it.toSubjectDomain() }
    }

    override suspend fun createRetake(request: CreateRetakeRequestDto): Retake {
        try {
            return KtorClient.createRetake(request).toRetakeDomain()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun updateRetake(id: Long, request: CreateRetakeRequestDto): Retake {
        return KtorClient.updateRetake(id, request).toRetakeDomain()
    }

    override suspend fun deleteRetake(id: Long) {
        KtorClient.deleteRetake(id)
    }

    override suspend fun getAllComments(): List<Comment> {
        return KtorClient.getAllComments().map { it.toCommentDomain() }
    }

    override suspend fun getAllRetakes(): List<Retake> {
        return KtorClient.getAllRetakes().map { it.toRetakeDomain() }
    }
}


