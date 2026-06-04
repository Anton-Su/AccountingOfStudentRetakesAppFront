package com.example.accountingofstudentretakesapp.domain.mapper

import com.example.accountingofstudentretakesapp.domain.model.RetakeEnrollmentDto
import com.example.accountingofstudentretakesapp.presentation.model.RetakeEnrollment

fun RetakeEnrollmentDto.toRetakeEnrollmentDomain() = RetakeEnrollment(
    id = id,
    retakeId = retakeId,
    studentId = studentId,
    studentSubjectId = studentSubjectId,
    studentFullName = studentFullName,
    groupName = groupName
)