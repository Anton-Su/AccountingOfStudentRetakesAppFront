package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.User

/** Репозиторий для работы с данными пользователя. */
interface UserRepository {
    /** Возвращает профиль текущего авторизованного пользователя. */
    suspend fun getCurrentUser(): User
}