package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toUserDomain
import com.example.accountingofstudentretakesapp.domain.model.User
import com.example.accountingofstudentretakesapp.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override suspend fun getCurrentUser(): User {
        return KtorClient.getProfile().toUserDomain()
    }
}

