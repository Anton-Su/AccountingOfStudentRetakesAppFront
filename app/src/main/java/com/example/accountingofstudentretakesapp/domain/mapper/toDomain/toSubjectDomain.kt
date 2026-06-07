package com.example.accountingofstudentretakesapp.domain.mapper.toDomain

import com.example.accountingofstudentretakesapp.data.model.SubjectDto
import com.example.accountingofstudentretakesapp.domain.model.Subject

fun SubjectDto.toSubjectDomain() = Subject(
    id = id,
    title = title,
)