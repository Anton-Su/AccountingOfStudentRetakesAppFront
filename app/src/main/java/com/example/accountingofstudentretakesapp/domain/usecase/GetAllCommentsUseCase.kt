package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

class GetAllCommentsUseCase(private val adminRepository: AdminRepository) {
    suspend operator fun invoke(): List<Comment> {
        return adminRepository.getAllComments()
    }
}

