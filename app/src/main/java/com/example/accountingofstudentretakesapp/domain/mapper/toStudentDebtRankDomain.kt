package com.example.accountingofstudentretakesapp.domain.mapper

import com.example.accountingofstudentretakesapp.data.model.StudentDebtRankDto
import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRank

fun StudentDebtRankDto.toStudentDebtRankDomain() = StudentDebtRank(
    studentId = studentId,
    debtsCount = debtsCount,
    place = place,
    totalStudents = totalStudents,
    topPercent = topPercent
)