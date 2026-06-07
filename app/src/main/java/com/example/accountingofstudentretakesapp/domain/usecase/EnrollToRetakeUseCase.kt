package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

/** Записывает студента на пересдачу. */
class EnrollToRetakeUseCase(private val studentRepository: StudentRepository) {
    /**
     * @param studentId идентификатор студента
     * @param debtId идентификатор долга
     * @param retakeId идентификатор пересдачи
     * @return true если запись прошла успешно
     */
    suspend operator fun invoke(studentId: Long, debtId: Long, retakeId: Long): Boolean {
        return studentRepository.enrollToRetake(studentId, debtId, retakeId)
    }
}
