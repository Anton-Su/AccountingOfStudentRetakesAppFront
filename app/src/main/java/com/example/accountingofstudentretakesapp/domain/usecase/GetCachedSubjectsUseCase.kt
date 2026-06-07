package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

class GetCachedSubjectsUseCase(private val localRepository: LocalRepository) {
    suspend operator fun invoke(): List<Subject> {
        return localRepository.getAllSubjects()
    }
}