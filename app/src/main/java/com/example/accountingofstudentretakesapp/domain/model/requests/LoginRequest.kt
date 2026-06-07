package com.example.accountingofstudentretakesapp.domain.model.requests

/**
 * Запрос на авторизацию пользователя.
 *
 * @property email электронная почта пользователя
 * @property password пароль пользователя
 */
data class LoginRequest(
    val email: String,
    val password: String
)