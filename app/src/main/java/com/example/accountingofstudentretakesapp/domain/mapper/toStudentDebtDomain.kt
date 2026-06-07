package com.example.accountingofstudentretakesapp.domain.mapper

import com.example.accountingofstudentretakesapp.data.model.StudentDebtDto
import com.example.accountingofstudentretakesapp.domain.model.StudentDebt

fun StudentDebtDto.toStudentDebtDomain() = StudentDebt(
    id = id,
    subjectId = subjectId,
    subjectTitle = subjectTitle,
)