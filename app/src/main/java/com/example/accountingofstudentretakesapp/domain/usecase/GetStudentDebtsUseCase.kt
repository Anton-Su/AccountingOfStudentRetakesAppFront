package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.StudentDebt
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

/** Возвращает список долгов студента. */
class GetStudentDebtsUseCase(private val studentRepository: StudentRepository) {
    /**
     * @param studentId идентификатор студента
     * @return список долгов
     */
    suspend operator fun invoke(studentId: Long): List<StudentDebt> {
        return studentRepository.findDebtsByStudentId(studentId)
    }
}

