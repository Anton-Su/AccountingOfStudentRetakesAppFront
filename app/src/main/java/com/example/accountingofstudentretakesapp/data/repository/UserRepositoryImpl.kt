package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toUserDomain
import com.example.accountingofstudentretakesapp.domain.model.UserDto
import com.example.accountingofstudentretakesapp.domain.repository.UserRepository
import com.example.accountingofstudentretakesapp.presentation.model.User

class UserRepositoryImpl : UserRepository {
    override suspend fun getCurrentUser(): User? {
        return try {
            KtorClient.getProfile().toUserDomain()
        } catch (e: Exception) {
            null
        }
    }
}

