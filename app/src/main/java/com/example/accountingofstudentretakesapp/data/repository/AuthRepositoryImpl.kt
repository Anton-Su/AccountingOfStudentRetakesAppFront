package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.data.remote.TokenManager
import com.example.accountingofstudentretakesapp.domain.mapper.toDto.toLoginRequestDto
import com.example.accountingofstudentretakesapp.domain.model.requests.LoginRequest
import com.example.accountingofstudentretakesapp.domain.repository.AuthRepository

/**
 * Реализация репозитория для авторизации пользователя.
 * Управляет JWT токеном через [TokenManager] и [KtorClient].
 *
 * @param tokenManager менеджер токенов для сохранения и очистки
 */
class AuthRepositoryImpl(private val tokenManager: TokenManager) : AuthRepository {

    /**
     * Авторизует пользователя и сохраняет JWT токен.
     * После успешной авторизации токен устанавливается в [KtorClient].
     *
     * @param request данные для входа (email и пароль)
     * @return JWT токен
     */
    override suspend fun login(request: LoginRequest): String {
        val response = KtorClient.login(request.toLoginRequestDto())
        tokenManager.saveAccessToken(response.token)
        KtorClient.updateAccessToken(response.token)
        return response.token
    }

    /**
     * Выходит из системы и очищает токен авторизации.
     * Токен удаляется из [TokenManager] и [KtorClient].
     */
    override suspend fun logout() {
        tokenManager.clearTokens()
        KtorClient.clearTokens()
    }
}