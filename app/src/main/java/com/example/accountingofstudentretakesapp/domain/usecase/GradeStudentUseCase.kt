package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.data.model.GradeRequestDto
import com.example.accountingofstudentretakesapp.domain.model.RetakeEnrollment
import com.example.accountingofstudentretakesapp.domain.repository.TeacherRepository

class GradeStudentUseCase(private val teacherRepository: TeacherRepository) {
    suspend operator fun invoke(retakeId: Long, studentId: Long, score: Int): RetakeEnrollment {
        val request = GradeRequestDto(score = score)
        return teacherRepository.gradeStudent(retakeId, studentId, request)
    }
}

