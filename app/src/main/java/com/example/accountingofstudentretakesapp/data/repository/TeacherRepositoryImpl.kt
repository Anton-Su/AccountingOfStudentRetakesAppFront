package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.model.GradeRequestDto
import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toRetakeDetailsDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toRetakeDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toRetakeEnrollmentDomain
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.RetakeDetails
import com.example.accountingofstudentretakesapp.domain.model.RetakeEnrollment
import com.example.accountingofstudentretakesapp.domain.repository.TeacherRepository


class TeacherRepositoryImpl : TeacherRepository {
    override suspend fun getTeacherRetakes(): List<Retake> {
        return KtorClient.getTeacherRetakes().map{it.toRetakeDomain()}
    }

    override suspend fun getRetakeDetails(retakeId: Long): RetakeDetails {
        return KtorClient.getRetakeDetails(retakeId).toRetakeDetailsDomain()
    }

    override suspend fun gradeStudent(retakeId: Long, studentId: Long, request: GradeRequestDto): RetakeEnrollment {
        return KtorClient.gradeStudent(retakeId, studentId, request).toRetakeEnrollmentDomain()
    }
}

