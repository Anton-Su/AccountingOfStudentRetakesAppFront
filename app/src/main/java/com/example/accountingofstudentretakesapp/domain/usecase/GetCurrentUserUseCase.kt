package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.User
import com.example.accountingofstudentretakesapp.domain.repository.UserRepository

/** Возвращает профиль текущего авторизованного пользователя. */
class GetCurrentUserUseCase(private val userRepository: UserRepository) {
    /** @return текущий пользователь */
    suspend operator fun invoke(): User = userRepository.getCurrentUser()
}