package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository


class GetEnrolledRetakesUseCase(private val studentRepository: StudentRepository) {
    suspend operator fun invoke(studentId: Long): List<Retake> {
        return studentRepository.findEnrolledRetakes(studentId)
    }
}

