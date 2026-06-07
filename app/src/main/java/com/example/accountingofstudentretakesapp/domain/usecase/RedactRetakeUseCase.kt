package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.requests.RedactRetakeRequest
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

/** Редактирует существующую пересдачу. */
class RedactRetakeUseCase(private val adminRepository: AdminRepository) {
    /**
     * @param request данные для редактирования включая идентификатор
     * @return обновлённая пересдача
     */
    suspend operator fun invoke(request: RedactRetakeRequest): Retake {
        return adminRepository.updateRetake(request)
    }
}