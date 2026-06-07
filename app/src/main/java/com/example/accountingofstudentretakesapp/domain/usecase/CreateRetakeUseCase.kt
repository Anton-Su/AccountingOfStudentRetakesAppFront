package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

class CreateRetakeUseCase(private val adminRepository: AdminRepository) {
    suspend operator fun invoke(request: CreateRetakeRequest): Retake {
        return adminRepository.createRetake(request)
    }
}

