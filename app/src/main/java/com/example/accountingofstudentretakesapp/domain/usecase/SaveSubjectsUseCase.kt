package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

class SaveSubjectsUseCase(private val localRepository: LocalRepository) {
    suspend operator fun invoke(subjects: List<Subject>) {
        localRepository.saveSubjects(subjects)
    }
}