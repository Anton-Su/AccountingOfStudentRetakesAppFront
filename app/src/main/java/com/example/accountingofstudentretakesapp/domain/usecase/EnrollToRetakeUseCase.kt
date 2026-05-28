package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

class EnrollToRetakeUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(studentId: Long, debtId: Long, retakeId: Long): Boolean {
        return studentRepository.enrollToRetake(studentId, debtId, retakeId)
    }
}

