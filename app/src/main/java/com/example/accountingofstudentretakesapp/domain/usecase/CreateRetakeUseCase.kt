package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.CreateRetakeRequestDto
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository
import com.example.accountingofstudentretakesapp.presentation.model.Retake
import java.time.Instant

class CreateRetakeUseCase(private val adminRepository: AdminRepository) {
    suspend operator fun invoke(startAt: Instant, endAt: Instant, teacherIds: List<Long>, subjectId: Long, type: String, place: String, admission: String? = null): Retake {
        return adminRepository.createRetake(startAt, endAt, teacherIds, subjectId, type, place, admission)
    }
}

