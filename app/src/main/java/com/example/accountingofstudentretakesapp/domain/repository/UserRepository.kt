package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.presentation.model.User

interface UserRepository {
    suspend fun getCurrentUser(): User?
}