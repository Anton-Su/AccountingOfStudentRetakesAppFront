package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.repository.TeacherRepository

/** Возвращает список пересдач преподавателя. */
class GetTeacherRetakesUseCase(private val teacherRepository: TeacherRepository) {
	/** @return список пересдач */
	suspend operator fun invoke(): List<Retake> {
		return teacherRepository.getTeacherRetakes()
	}
}



