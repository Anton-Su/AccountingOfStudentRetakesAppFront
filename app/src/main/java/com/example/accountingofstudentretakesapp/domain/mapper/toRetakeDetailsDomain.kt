package com.example.accountingofstudentretakesapp.domain.mapper

import com.example.accountingofstudentretakesapp.data.model.RetakeDetailsDto
import com.example.accountingofstudentretakesapp.domain.model.RetakeDetails

fun RetakeDetailsDto.toRetakeDetailsDomain() = RetakeDetails(
    retake = retake.toRetakeDomain(),
    enrollments = enrollments.map { it.toRetakeEnrollmentDomain() })