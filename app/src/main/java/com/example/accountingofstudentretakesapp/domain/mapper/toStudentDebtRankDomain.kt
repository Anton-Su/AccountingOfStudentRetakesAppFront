package com.example.accountingofstudentretakesapp.domain.mapper

import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRankDto
import com.example.accountingofstudentretakesapp.presentation.model.StudentDebtRank

fun StudentDebtRankDto.toStudentDebtRankDomain() = StudentDebtRank(
    studentId = studentId,
    debtsCount = debtsCount,
    place = place,
    totalStudents = totalStudents,
    topPercent = topPercent
)