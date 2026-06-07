package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

/** Возвращает список преподавателей по дисциплине. */
class GetTeachersByDisciplineUseCase(private val adminRepository: AdminRepository) {
    /**
     * @param discipline название дисциплины
     * @return список преподавателей
     */
    suspend operator fun invoke(discipline: String): List<Teacher> {
        return adminRepository.getTeachersByDiscipline(discipline)
    }
}
