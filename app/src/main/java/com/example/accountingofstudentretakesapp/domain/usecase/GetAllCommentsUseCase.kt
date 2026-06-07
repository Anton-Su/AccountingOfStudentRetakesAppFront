package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

/** Возвращает все комментарии с сервера. */
class GetAllCommentsUseCase(private val adminRepository: AdminRepository) {
    /** @return список всех комментариев */
    suspend operator fun invoke(): List<Comment> {
        return adminRepository.getAllComments()
    }
}

