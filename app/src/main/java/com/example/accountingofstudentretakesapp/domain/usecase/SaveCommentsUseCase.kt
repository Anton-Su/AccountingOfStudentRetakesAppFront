package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

class SaveCommentsUseCase(private val localRepository: LocalRepository) {
    suspend operator fun invoke(comments: List<Comment>) {
        localRepository.saveComments(comments)
    }
}