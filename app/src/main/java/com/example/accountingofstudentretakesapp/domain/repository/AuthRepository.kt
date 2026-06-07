package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.requests.LoginRequest

/** Репозиторий для авторизации пользователя. */
interface AuthRepository {
    /**
     * Авторизует пользователя и возвращает JWT токен.
     * @param request данные для входа
     * @return JWT токен
     */
    suspend fun login(request: LoginRequest): String

    /** Выходит из системы и очищает токен авторизации. */
    suspend fun logout()
}