package com.example.accountingofstudentretakesapp.domain.mapper

import com.example.accountingofstudentretakesapp.domain.model.StudentDebtDto
import com.example.accountingofstudentretakesapp.presentation.model.StudentDebt

fun StudentDebtDto.toStudentDebtDomain() = StudentDebt(
    id = id,
    subjectId = subjectId,
    subjectTitle = subjectTitle,
)