package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.data.model.requests.GradeRequestDto
import com.example.accountingofstudentretakesapp.domain.model.RetakeEnrollment
import com.example.accountingofstudentretakesapp.domain.model.requests.GradeRequest
import com.example.accountingofstudentretakesapp.domain.repository.TeacherRepository

class GradeStudentUseCase(private val teacherRepository: TeacherRepository) {
    suspend operator fun invoke(retakeId: Long, studentId: Long, request: GradeRequest): RetakeEnrollment {
        return teacherRepository.gradeStudent(retakeId, studentId, request)
    }
}

