package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

/** Сохраняет список преподавателей в локальный кэш. */
class SaveTeachersUseCase(private val localRepository: LocalRepository) {
    /** @param teachers список преподавателей для сохранения */
    suspend operator fun invoke(teachers: List<Teacher>) {
        localRepository.saveTeachers(teachers)
    }
}