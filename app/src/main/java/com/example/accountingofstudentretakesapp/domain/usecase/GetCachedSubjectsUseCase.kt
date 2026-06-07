package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

/** Возвращает все предметы из локального кэша. */
class GetCachedSubjectsUseCase(private val localRepository: LocalRepository) {
    /** @return список кэшированных предметов */
    suspend operator fun invoke(): List<Subject> {
        return localRepository.getAllSubjects()
    }
}