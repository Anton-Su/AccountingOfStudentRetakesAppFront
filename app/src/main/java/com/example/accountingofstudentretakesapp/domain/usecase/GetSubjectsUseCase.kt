package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository
import com.example.accountingofstudentretakesapp.domain.repository.GuestRepository

class GetSubjectsUseCase(private val guestRepository: GuestRepository) {
    suspend operator fun invoke(): List<Subject> {
        return guestRepository.getSubjects()
    }
}

