package com.example.accountingofstudentretakesapp.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class RedactRetakeRequestDto(
    val startAt: String,
    val endAt: String,
    val teacherIds: List<Long>,
    val subjectId: Long,
    val type: String,
    val place: String,
    val admission: String? = null
)