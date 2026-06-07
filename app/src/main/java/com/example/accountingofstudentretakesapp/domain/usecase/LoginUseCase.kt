package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.requests.LoginRequest
import com.example.accountingofstudentretakesapp.domain.repository.AuthRepository

/** Авторизует пользователя и возвращает JWT токен. */
class LoginUseCase(private val repository: AuthRepository) {
    /**
     * @param request данные для входа
     * @return JWT токен
     */
    suspend operator fun invoke(request: LoginRequest): String {
        return repository.login(request)
    }
}