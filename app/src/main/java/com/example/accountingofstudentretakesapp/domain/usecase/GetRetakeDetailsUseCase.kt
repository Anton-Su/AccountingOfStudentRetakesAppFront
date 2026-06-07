package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.RetakeDetails
import com.example.accountingofstudentretakesapp.domain.repository.TeacherRepository

/** Возвращает детальную информацию о пересдаче для преподавателя. */
class GetRetakeDetailsUseCase(private val teacherRepository: TeacherRepository) {
    /**
     * @param retakeId идентификатор пересдачи
     * @return детали пересдачи включая список студентов
     */
    suspend operator fun invoke(retakeId: Long): RetakeDetails {
        return teacherRepository.getRetakeDetails(retakeId)
    }
}

