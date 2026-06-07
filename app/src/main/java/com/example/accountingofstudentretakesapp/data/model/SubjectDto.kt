package com.example.accountingofstudentretakesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SubjectDto(
    val id: Long,
    val title: String
)
