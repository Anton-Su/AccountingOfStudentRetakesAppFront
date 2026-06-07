package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

/** Сохраняет список предметов в локальный кэш. */
class SaveSubjectsUseCase(private val localRepository: LocalRepository) {
    /** @param subjects список предметов для сохранения */
    suspend operator fun invoke(subjects: List<Subject>) {
        localRepository.saveSubjects(subjects)
    }
}