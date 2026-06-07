package com.example.accountingofstudentretakesapp.domain.mapper.toDto

import com.example.accountingofstudentretakesapp.data.model.requests.CreateRetakeRequestDto
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest

fun CreateRetakeRequest.toСreateRetakeRequestDto() = CreateRetakeRequestDto(
    startAt = startAt.toString(),
    endAt = endAt.toString(),
    teacherIds = teacherIds,
    subjectId = subjectId,
    type = type,
    place = place,
    admission = admission
)