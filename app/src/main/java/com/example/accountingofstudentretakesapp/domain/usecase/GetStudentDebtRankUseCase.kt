package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository
import com.example.accountingofstudentretakesapp.presentation.model.StudentDebtRank

class GetStudentDebtRankUseCase(private val studentRepository: StudentRepository) {
    suspend operator fun invoke(studentId: Long): StudentDebtRank {
        return studentRepository.getStudentDebtRank(studentId)
    }
}

