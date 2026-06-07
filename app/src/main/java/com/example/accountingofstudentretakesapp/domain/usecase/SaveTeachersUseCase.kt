package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

class SaveTeachersUseCase(private val localRepository: LocalRepository) {
    suspend operator fun invoke(teachers: List<Teacher>) {
        localRepository.saveTeachers(teachers)
    }
}