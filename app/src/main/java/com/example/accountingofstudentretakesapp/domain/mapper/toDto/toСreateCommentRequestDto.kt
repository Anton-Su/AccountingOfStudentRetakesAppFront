package com.example.accountingofstudentretakesapp.domain.mapper.toDto

import com.example.accountingofstudentretakesapp.data.model.requests.CreateCommentRequestDto
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateCommentRequest

fun CreateCommentRequest.toСreateCommentRequestDto() = CreateCommentRequestDto(
    gradePlace = gradePlace,
    gradeTeacher = gradeTeacher,
    gradeOverall = gradeOverall,
    comment = comment,
    retakeId = retakeId
)