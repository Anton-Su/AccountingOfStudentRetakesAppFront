package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.CreateCommentRequestDto
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository
import com.example.accountingofstudentretakesapp.presentation.model.Comment

class CreateCommentUseCase(private val studentRepository: StudentRepository) {
	suspend operator fun invoke(studentId: Long, request: CreateCommentRequestDto): Comment {
		return studentRepository.createComment(studentId, request)
	}
}