package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toDto.toGradeRequestDto
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toRetakeDetailsDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toRetakeDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toRetakeEnrollmentDomain
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.RetakeDetails
import com.example.accountingofstudentretakesapp.domain.model.RetakeEnrollment
import com.example.accountingofstudentretakesapp.domain.model.requests.GradeRequest
import com.example.accountingofstudentretakesapp.domain.repository.TeacherRepository


class TeacherRepositoryImpl : TeacherRepository {
    override suspend fun getTeacherRetakes(): List<Retake> {
        return KtorClient.getTeacherRetakes().map { it.toRetakeDomain()}
    }

    override suspend fun getRetakeDetails(retakeId: Long): RetakeDetails {
        return KtorClient.getRetakeDetails(retakeId).toRetakeDetailsDomain()
    }

    override suspend fun gradeStudent(retakeId: Long, studentId: Long, request: GradeRequest): RetakeEnrollment {
        return KtorClient.gradeStudent(retakeId, studentId, request.toGradeRequestDto()).toRetakeEnrollmentDomain()
    }
}

