package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.RetakeDetailDto
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

class GetEnrolledRetakesUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(studentId: Long): List<RetakeDetailDto> {
        return studentRepository.findEnrolledRetakes(studentId)
    }
}

