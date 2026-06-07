package com.example.accountingofstudentretakesapp.domain.mapper.toDomain

import com.example.accountingofstudentretakesapp.data.model.RetakeEnrollmentDto
import com.example.accountingofstudentretakesapp.domain.model.RetakeEnrollment

fun RetakeEnrollmentDto.toRetakeEnrollmentDomain() = RetakeEnrollment(
    id = id,
    retakeId = retakeId,
    studentId = studentId,
    studentSubjectId = studentSubjectId,
    studentFullName = studentFullName,
    groupName = groupName
)