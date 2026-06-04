package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository
import com.example.accountingofstudentretakesapp.presentation.model.Subject

class GetSubjectsUseCase(private val adminRepository: AdminRepository) {
    suspend operator fun invoke(): List<Subject> {
        return adminRepository.getSubjects()
    }
}

