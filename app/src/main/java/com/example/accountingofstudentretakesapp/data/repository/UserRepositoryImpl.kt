package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toUserDomain
import com.example.accountingofstudentretakesapp.domain.model.User
import com.example.accountingofstudentretakesapp.domain.repository.UserRepository

/**
 * Реализация репозитория для операций с пользователем.
 * Взаимодействует с сервером через [KtorClient].
 */
class UserRepositoryImpl : UserRepository {

    /**
     * Возвращает профиль текущего авторизованного пользователя.
     *
     * @return текущий пользователь
     */
    override suspend fun getCurrentUser(): User {
        return KtorClient.getProfile().toUserDomain()
    }
}
