package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

/** Возвращает все пересдачи с сервера. */
class GetAllRetakesUseCase(private val adminRepository: AdminRepository) {
    /** @return список всех пересдач */
    suspend operator fun invoke(): List<Retake> {
        return adminRepository.getAllRetakes()
    }
}

