package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

/** Удаляет пересдачу по идентификатору. */
class DeleteRetakeUseCase(private val adminRepository: AdminRepository) {
    /**
     * @param id идентификатор пересдачи
     */
    suspend operator fun invoke(id: Long) {
        adminRepository.deleteRetake(id)
    }
}

