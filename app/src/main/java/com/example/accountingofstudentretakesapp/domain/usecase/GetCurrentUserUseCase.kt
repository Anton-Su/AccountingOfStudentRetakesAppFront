package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.UserRepository
import com.example.accountingofstudentretakesapp.presentation.model.User

class GetCurrentUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): User? = userRepository.getCurrentUser()
}

