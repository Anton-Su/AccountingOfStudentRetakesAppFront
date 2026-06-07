package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

/** Возвращает список доступных пересдач для студента. */
class GetAvailableRetakesUseCase(private val studentRepository: StudentRepository) {
    /**
     * @param studentId идентификатор студента
     * @return список доступных пересдач
     */
    suspend operator fun invoke(studentId: Long): List<Retake> {
        return studentRepository.findAvailableRetakes(studentId)
    }
}
