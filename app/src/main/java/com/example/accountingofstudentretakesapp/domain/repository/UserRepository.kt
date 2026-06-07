package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.User


interface UserRepository {
    suspend fun getCurrentUser(): User
}