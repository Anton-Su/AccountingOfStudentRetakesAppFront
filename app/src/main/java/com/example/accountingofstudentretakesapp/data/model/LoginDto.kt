package com.example.accountingofstudentretakesapp.data.model

import kotlinx.serialization.Serializable
/**
 * DTO ответа на запрос авторизации.
 *
 * @property token JWT токен для последующих запросов
 */
@Serializable
data class LoginDto(
    val token: String
)
