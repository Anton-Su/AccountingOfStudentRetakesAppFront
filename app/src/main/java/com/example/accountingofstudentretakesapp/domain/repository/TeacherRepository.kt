package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.data.model.GradeRequestDto
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.RetakeDetails
import com.example.accountingofstudentretakesapp.domain.model.RetakeEnrollment

interface TeacherRepository {
	suspend fun getTeacherRetakes(): List<Retake>
	suspend fun getRetakeDetails(retakeId: Long): RetakeDetails
	suspend fun gradeStudent(retakeId: Long, studentId: Long, request: GradeRequestDto): RetakeEnrollment
}


