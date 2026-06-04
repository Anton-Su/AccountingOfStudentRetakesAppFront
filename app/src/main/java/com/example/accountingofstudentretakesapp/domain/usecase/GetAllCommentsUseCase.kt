package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository
import com.example.accountingofstudentretakesapp.presentation.model.Comment

class GetAllCommentsUseCase(private val adminRepository: AdminRepository) {
    suspend operator fun invoke(): List<Comment> {
        return adminRepository.getAllComments()
    }
}

