package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.RetakeDetailDto
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

class GetAvailableRetakesUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(studentId: Long): List<RetakeDetailDto> {
        return studentRepository.findAvailableRetakes(studentId)
    }
}

