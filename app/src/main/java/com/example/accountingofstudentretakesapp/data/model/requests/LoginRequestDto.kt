package com.example.accountingofstudentretakesapp.data.model.requests

import kotlinx.serialization.Serializable

/**
 * DTO для авторизации пользователя.
 * Отправляется на сервер при входе в систему.
 *
 * @property email электронная почта пользователя
 * @property password пароль пользователя
 */

@Serializable
data class LoginRequestDto(
    val email: String,
    val password: String
)
