package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.presentation.model.UserRole

interface AuthRepository {
    suspend fun login(email: String, password: String, role: UserRole): Result<String>
    suspend fun logout()
}