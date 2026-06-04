package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.repository.TeacherRepository
import com.example.accountingofstudentretakesapp.presentation.model.RetakeDetails

class GetRetakeDetailsUseCase(private val teacherRepository: TeacherRepository) {
    suspend operator fun invoke(retakeId: Long): RetakeDetails {
        return teacherRepository.getRetakeDetails(retakeId)
    }
}

