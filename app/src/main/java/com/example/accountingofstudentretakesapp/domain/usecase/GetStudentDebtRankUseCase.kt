package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRank
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

class GetStudentDebtRankUseCase(private val studentRepository: StudentRepository) {
    suspend operator fun invoke(studentId: Long): StudentDebtRank {
        return studentRepository.getStudentDebtRank(studentId)
    }
}

