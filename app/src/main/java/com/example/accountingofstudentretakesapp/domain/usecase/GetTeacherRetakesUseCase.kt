package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.TeacherRepository
import com.example.accountingofstudentretakesapp.presentation.model.Retake

class GetTeacherRetakesUseCase(private val teacherRepository: TeacherRepository) {
	suspend operator fun invoke(): List<Retake> {
		return teacherRepository.getTeacherRetakes()
	}
}


