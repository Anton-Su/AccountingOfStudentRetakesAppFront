package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

class GetCachedTeachersByDisciplineUseCase(private val localRepository: LocalRepository) {
    suspend operator fun invoke(discipline: String): List<Teacher> {
        return localRepository.getTeachersByDiscipline(discipline)
    }
}