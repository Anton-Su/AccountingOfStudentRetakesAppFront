package com.example.accountingofstudentretakesapp.domain.mapper.toDto

import com.example.accountingofstudentretakesapp.data.model.requests.RedactRetakeRequestDto
import com.example.accountingofstudentretakesapp.domain.model.requests.RedactRetakeRequest

fun RedactRetakeRequest.toRedactRetakeDto() = RedactRetakeRequestDto(
    startAt = startAt.toString(),
    endAt = endAt.toString(),
    teacherIds = teacherIds,
    subjectId = subjectId,
    type = type,
    place = place,
    admission = admission
)
