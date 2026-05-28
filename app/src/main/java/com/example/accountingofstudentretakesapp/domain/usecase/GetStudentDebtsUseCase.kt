package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.StudentDebtDto
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

class GetStudentDebtsUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(studentId: Long): List<StudentDebtDto> {
        return studentRepository.findDebtsByStudentId(studentId)
    }
}

