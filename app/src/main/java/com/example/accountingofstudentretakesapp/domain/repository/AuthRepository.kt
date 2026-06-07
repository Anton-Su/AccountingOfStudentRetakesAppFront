package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.requests.LoginRequest


interface AuthRepository {
    suspend fun login(request: LoginRequest): String
    suspend fun logout()
}