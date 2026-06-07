package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

/** Возвращает все комментарии из локального кэша. */
class GetCachedCommentsUseCase(private val localRepository: LocalRepository) {
    /** @return список кэшированных комментариев */
    suspend operator fun invoke(): List<Comment> {
        return localRepository.getAllComments()
    }
}
