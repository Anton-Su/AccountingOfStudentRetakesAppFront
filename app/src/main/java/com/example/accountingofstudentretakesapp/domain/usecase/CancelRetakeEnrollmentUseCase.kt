package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

/** Отменяет запись студента на пересдачу. */
class CancelRetakeEnrollmentUseCase(private val studentRepository: StudentRepository) {
    /**
     * @param studentId идентификатор студента
     * @param debtId идентификатор долга
     * @param retakeId идентификатор пересдачи
     * @return true если отмена прошла успешно
     */
    suspend operator fun invoke(studentId: Long, debtId: Long, retakeId: Long): Boolean {
        return studentRepository.cancelRetakeEnrollment(studentId, debtId, retakeId)
    }
}

