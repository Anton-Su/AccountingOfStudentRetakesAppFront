package com.example.accountingofstudentretakesapp.domain.mapper

import com.example.accountingofstudentretakesapp.domain.model.SubjectDto
import com.example.accountingofstudentretakesapp.presentation.model.Subject

fun SubjectDto.toSubjectDomain() = Subject(
    id = id,
    title = title,
)