package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.repository.TeacherRepository

class GetTeacherRetakesUseCase(private val teacherRepository: TeacherRepository) {
	suspend operator fun invoke(): List<Retake> {
		return teacherRepository.getTeacherRetakes()
	}
}


