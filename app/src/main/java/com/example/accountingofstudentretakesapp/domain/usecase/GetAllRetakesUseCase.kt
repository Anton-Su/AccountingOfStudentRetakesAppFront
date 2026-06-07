package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

class GetAllRetakesUseCase(private val adminRepository: AdminRepository) {
    suspend operator fun invoke(): List<Retake> {
        return adminRepository.getAllRetakes()
    }
}

