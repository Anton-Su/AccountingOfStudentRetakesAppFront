package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.User
import com.example.accountingofstudentretakesapp.domain.repository.UserRepository

class GetCurrentUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): User = userRepository.getCurrentUser()
}

