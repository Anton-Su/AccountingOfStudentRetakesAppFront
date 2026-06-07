package com.example.accountingofstudentretakesapp.data.local.mapper

import com.example.accountingofstudentretakesapp.data.local.entity.SubjectEntity
import com.example.accountingofstudentretakesapp.domain.model.Subject

/** Конвертирует Room entity [SubjectEntity] в domain модель [Subject]. */
fun SubjectEntity.toSubjectDomain() = Subject(
    id = id,
    title = title
)