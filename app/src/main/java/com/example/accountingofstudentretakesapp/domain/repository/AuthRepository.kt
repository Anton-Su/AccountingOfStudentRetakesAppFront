package com.example.accountingofstudentretakesapp.domain.repository


interface AuthRepository {
    suspend fun login(email: String, password: String): String
    suspend fun logout()
}