package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

/** Сохраняет список комментариев в локальный кэш. */
class SaveCommentsUseCase(private val localRepository: LocalRepository) {
    /** @param comments список комментариев для сохранения */
    suspend operator fun invoke(comments: List<Comment>) {
        localRepository.saveComments(comments)
    }
}
