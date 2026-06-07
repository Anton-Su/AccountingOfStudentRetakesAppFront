package com.example.accountingofstudentretakesapp.domain.model

data class RetakeDetails(
    val retake: Retake,
    val enrollments: List<RetakeEnrollment>
)