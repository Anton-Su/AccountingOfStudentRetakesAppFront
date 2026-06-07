package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

/** Создаёт новую пересдачу. */
class CreateRetakeUseCase(private val adminRepository: AdminRepository) {
    /**
     * @param request данные для создания пересдачи
     * @return созданная пересдача
     */
    suspend operator fun invoke(request: CreateRetakeRequest): Retake {
        return adminRepository.createRetake(request)
    }
}