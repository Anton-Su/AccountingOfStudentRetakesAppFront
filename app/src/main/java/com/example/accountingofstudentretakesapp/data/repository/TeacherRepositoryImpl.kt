package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toRetakeDetailsDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toRetakeDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toRetakeEnrollmentDomain
import com.example.accountingofstudentretakesapp.domain.model.GradeRequestDto
import com.example.accountingofstudentretakesapp.domain.repository.TeacherRepository
import com.example.accountingofstudentretakesapp.presentation.model.Retake
import com.example.accountingofstudentretakesapp.presentation.model.RetakeDetails
import com.example.accountingofstudentretakesapp.presentation.model.RetakeEnrollment
import com.example.accountingofstudentretakesapp.presentation.ui.component.InfoTile

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

