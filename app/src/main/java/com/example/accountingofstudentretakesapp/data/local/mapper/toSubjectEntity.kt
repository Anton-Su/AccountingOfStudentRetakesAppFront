package com.example.accountingofstudentretakesapp.data.local.mapper

import com.example.accountingofstudentretakesapp.data.local.entity.SubjectEntity
import com.example.accountingofstudentretakesapp.domain.model.Subject

/** Конвертирует domain модель [Subject] в Room entity [SubjectEntity]. */
fun Subject.toSubjectEntity() = SubjectEntity(
    id = id,
    title = title
)