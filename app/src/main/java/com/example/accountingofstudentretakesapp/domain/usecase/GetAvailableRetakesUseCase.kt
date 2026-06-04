package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository
import com.example.accountingofstudentretakesapp.presentation.model.Retake

class GetAvailableRetakesUseCase(private val studentRepository: StudentRepository) {
    suspend operator fun invoke(studentId: Long): List<Retake> {
        return studentRepository.findAvailableRetakes(studentId)
    }
}

