package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateCommentRequest
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

class CreateCommentUseCase(private val studentRepository: StudentRepository) {
	suspend operator fun invoke(studentId: Long, request: CreateCommentRequest): Comment {
		return studentRepository.createComment(studentId, request)
	}
}