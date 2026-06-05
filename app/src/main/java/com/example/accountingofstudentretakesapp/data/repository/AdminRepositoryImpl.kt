package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toCommentDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toRetakeDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toSubjectDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toTeacherDomain
import com.example.accountingofstudentretakesapp.domain.model.CreateRetakeRequestDto
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository
import com.example.accountingofstudentretakesapp.presentation.model.Comment
import com.example.accountingofstudentretakesapp.presentation.model.Retake
import com.example.accountingofstudentretakesapp.presentation.model.Subject
import com.example.accountingofstudentretakesapp.presentation.model.Teacher
import java.time.Instant

class AdminRepositoryImpl : AdminRepository {
    override suspend fun getTeachersByDiscipline(discipline: String): List<Teacher> {
        return KtorClient.getTeachersByDiscipline(discipline).map { it.toTeacherDomain() }
    }

    override suspend fun getSubjects(): List<Subject> {
        return KtorClient.getSubjects().map { it.toSubjectDomain() }
    }

    override suspend fun createRetake(startAt: Instant, endAt: Instant, teacherIds: List<Long>, subjectId: Long, type: String, place: String, admission: String?): Retake {
        val request = CreateRetakeRequestDto(
            startAt = startAt.toString(),
            endAt = endAt.toString(),
            teacherIds = teacherIds,
            subjectId = subjectId,
            type = type,
            place = place,
            admission = admission
        )
        return KtorClient.createRetake(request).toRetakeDomain()
    }

    override suspend fun updateRetake(id: Long, startAt: Instant, endAt: Instant, teacherIds: List<Long>, subjectId: Long, type: String, place: String, admission: String?): Retake {
        val request = CreateRetakeRequestDto(
            startAt = startAt.toString(),
            endAt = endAt.toString(),
            teacherIds = teacherIds,
            subjectId = subjectId,
            type = type,
            place = place,
            admission = admission
        )
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


