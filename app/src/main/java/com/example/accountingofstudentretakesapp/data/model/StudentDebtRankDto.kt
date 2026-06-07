package com.example.accountingofstudentretakesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class StudentDebtRankDto(
    val studentId: Long,
    val debtsCount: Int,
    val place: Int,
    val totalStudents: Int,
    val topPercent: Int
)