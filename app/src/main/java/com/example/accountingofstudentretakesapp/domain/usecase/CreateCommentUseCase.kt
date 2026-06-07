package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.data.model.CreateCommentRequestDto
import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

class CreateCommentUseCase(private val studentRepository: StudentRepository) {
	suspend operator fun invoke(studentId: Long, request: CreateCommentRequestDto): Comment {
		return studentRepository.createComment(studentId, request)
	}
}