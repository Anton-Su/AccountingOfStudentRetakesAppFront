package com.example.accountingofstudentretakesapp.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class GradeRequestDto(
    val score: Int
)