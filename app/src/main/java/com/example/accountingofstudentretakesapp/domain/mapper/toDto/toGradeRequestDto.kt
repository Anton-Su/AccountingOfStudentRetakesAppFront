package com.example.accountingofstudentretakesapp.domain.mapper.toDto

import com.example.accountingofstudentretakesapp.data.model.requests.GradeRequestDto
import com.example.accountingofstudentretakesapp.domain.model.requests.GradeRequest

fun GradeRequest.toGradeRequestDto() = GradeRequestDto(
    score = score
)