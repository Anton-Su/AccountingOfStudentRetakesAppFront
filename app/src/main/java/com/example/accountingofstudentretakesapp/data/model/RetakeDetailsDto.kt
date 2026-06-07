package com.example.accountingofstudentretakesapp.data.model
import kotlinx.serialization.Serializable

@Serializable
data class RetakeDetailsDto(
    val retake: RetakeDto,
    val enrollments: List<RetakeEnrollmentDto>
)
