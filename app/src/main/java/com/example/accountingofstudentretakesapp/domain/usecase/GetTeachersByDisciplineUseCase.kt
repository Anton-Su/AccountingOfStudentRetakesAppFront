package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

class GetTeachersByDisciplineUseCase(private val adminRepository: AdminRepository) {
    suspend operator fun invoke(discipline: String): List<Teacher> {
        return adminRepository.getTeachersByDiscipline(discipline)
    }
}

