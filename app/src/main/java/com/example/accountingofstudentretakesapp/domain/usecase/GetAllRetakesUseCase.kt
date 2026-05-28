package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.RetakeDetailDto
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository
import com.example.accountingofstudentretakesapp.presentation.model.Retake

class GetAllRetakesUseCase(
    private val adminRepository: AdminRepository
) {
    suspend operator fun invoke(): List<RetakeDetailDto> {
        return adminRepository.getAllRetakes()
    }
}

