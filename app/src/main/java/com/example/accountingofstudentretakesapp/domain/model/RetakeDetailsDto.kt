package com.example.accountingofstudentretakesapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RetakeDetailsDto(
    val retake: RetakeDto,
    val enrollments: List<RetakeEnrollmentDto>
)

