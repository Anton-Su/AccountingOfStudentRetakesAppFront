package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

/** Возвращает преподавателей по дисциплине из локального кэша. */
class GetCachedTeachersByDisciplineUseCase(private val localRepository: LocalRepository) {
    /**
     * @param discipline название дисциплины
     * @return список кэшированных преподавателей
     */
    suspend operator fun invoke(discipline: String): List<Teacher> {
        return localRepository.getTeachersByDiscipline(discipline)
    }
}
