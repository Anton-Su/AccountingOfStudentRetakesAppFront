package com.example.accountingofstudentretakesapp.data.model

import com.example.accountingofstudentretakesapp.domain.model.UserRole
import kotlinx.serialization.Serializable

/**
 * DTO пользователя.
 * Приходит с сервера при запросе информации о текущем пользователе.
 *
 * @property id уникальный идентификатор пользователя
 * @property role роль пользователя в системе
 * @property firstName имя пользователя
 * @property secondName отчество пользователя
 * @property lastName фамилия пользователя
 * @property gender пол пользователя
 * @property age возраст пользователя
 * @property email электронная почта пользователя
 */
@Serializable
data class UserDto(
    val id: Long,
    val role: UserRole,
    val firstName: String,
    val secondName: String,
    val lastName: String,
    val gender: String,
    val age: Int,
    val email: String,
)