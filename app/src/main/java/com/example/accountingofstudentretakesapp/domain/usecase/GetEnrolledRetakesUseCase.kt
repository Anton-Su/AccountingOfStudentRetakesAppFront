package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

/** Возвращает список пересдач на которые записан студент. */
class GetEnrolledRetakesUseCase(private val studentRepository: StudentRepository) {
    /**
     * @param studentId идентификатор студента
     * @return список записанных пересдач
     */
    suspend operator fun invoke(studentId: Long): List<Retake> {
        return studentRepository.findEnrolledRetakes(studentId)
    }
}