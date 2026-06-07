package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRank
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

/** Возвращает рейтинг студента по количеству долгов. */
class GetStudentDebtRankUseCase(private val studentRepository: StudentRepository) {
    /**
     * @param studentId идентификатор студента
     * @return рейтинг студента
     */
    suspend operator fun invoke(studentId: Long): StudentDebtRank {
        return studentRepository.getStudentDebtRank(studentId)
    }
}

