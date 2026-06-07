package com.example.accountingofstudentretakesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class StudentDebtDto(
    val id: Long,
    val subjectId: Long,
    val subjectTitle: String,
)
