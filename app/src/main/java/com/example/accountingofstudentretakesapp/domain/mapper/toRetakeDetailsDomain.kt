package com.example.accountingofstudentretakesapp.domain.mapper

import com.example.accountingofstudentretakesapp.domain.model.RetakeDetailsDto
import com.example.accountingofstudentretakesapp.presentation.model.RetakeDetails

fun RetakeDetailsDto.toRetakeDetailsDomain() = RetakeDetails(
    retake = retake.toRetakeDomain(),
    enrollments = enrollments.map { it.toRetakeEnrollmentDomain() })