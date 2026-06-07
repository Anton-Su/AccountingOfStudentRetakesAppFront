package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.data.remote.TokenManager
import com.example.accountingofstudentretakesapp.domain.mapper.toDto.toLoginRequestDto
import com.example.accountingofstudentretakesapp.domain.model.requests.LoginRequest
import com.example.accountingofstudentretakesapp.domain.repository.AuthRepository

class AuthRepositoryImpl(private val tokenManager: TokenManager) : AuthRepository {
    override suspend fun login(request: LoginRequest): String {
        val response = KtorClient.login(request.toLoginRequestDto())
        tokenManager.saveAccessToken(response.token)
        KtorClient.updateAccessToken(response.token)
        return response.token
    }

    override suspend fun logout() {
        tokenManager.clearTokens()
        KtorClient.clearTokens()
    }
}
