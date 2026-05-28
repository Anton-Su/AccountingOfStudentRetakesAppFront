package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRankDto
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

class GetStudentDebtRankUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(studentId: Long): StudentDebtRankDto {
        return studentRepository.getStudentDebtRank(studentId)
    }
}

