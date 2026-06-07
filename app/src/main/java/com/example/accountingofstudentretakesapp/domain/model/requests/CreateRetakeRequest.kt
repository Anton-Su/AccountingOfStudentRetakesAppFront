package com.example.accountingofstudentretakesapp.domain.model.requests

import java.time.Instant

data class CreateRetakeRequest(
    val startAt: Instant,
    val endAt: Instant,
    val teacherIds: List<Long>,
    val subjectId: Long,
    val type: String,
    val place: String,
    val admission: String? = null
)