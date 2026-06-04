package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository
import com.example.accountingofstudentretakesapp.presentation.model.StudentDebt

class GetStudentDebtsUseCase(private val studentRepository: StudentRepository) {
    suspend operator fun invoke(studentId: Long): List<StudentDebt> {
        return studentRepository.findDebtsByStudentId(studentId)
    }
}

