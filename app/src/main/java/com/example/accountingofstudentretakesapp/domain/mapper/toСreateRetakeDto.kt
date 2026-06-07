package com.example.accountingofstudentretakesapp.domain.mapper

import com.example.accountingofstudentretakesapp.data.model.CreateRetakeRequestDto
import com.example.accountingofstudentretakesapp.domain.model.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.Retake


fun CreateRetakeRequest.toСreateRetakeDto() = CreateRetakeRequestDto(
    startAt = startAt.toString(),
    endAt = endAt.toString(),
    teacherIds = teacherIds,
    subjectId = subjectId,
    type = type,
    place = place,
    admission = admission
)
