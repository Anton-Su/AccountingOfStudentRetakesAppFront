package com.example.accountingofstudentretakesapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SubjectDto(
    val id: Long,
    val title: String
)
