package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.requests.LoginRequest
import com.example.accountingofstudentretakesapp.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(request: LoginRequest): String {
        return repository.login(request)
    }
}