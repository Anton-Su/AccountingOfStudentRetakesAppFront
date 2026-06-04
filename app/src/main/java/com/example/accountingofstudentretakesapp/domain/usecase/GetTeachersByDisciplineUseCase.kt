package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository
import com.example.accountingofstudentretakesapp.presentation.model.Teacher

class GetTeachersByDisciplineUseCase(private val adminRepository: AdminRepository) {
    suspend operator fun invoke(discipline: String): List<Teacher> {
        return adminRepository.getTeachersByDiscipline(discipline)
    }
}

