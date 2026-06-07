package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateCommentRequest
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

/** Создаёт комментарий к пересдаче от имени студента. */
class CreateCommentUseCase(private val studentRepository: StudentRepository) {
	/**
	 * @param studentId идентификатор студента
	 * @param request данные комментария
	 * @return созданный комментарий
	 */
	suspend operator fun invoke(studentId: Long, request: CreateCommentRequest): Comment {
		return studentRepository.createComment(studentId, request)
	}
}