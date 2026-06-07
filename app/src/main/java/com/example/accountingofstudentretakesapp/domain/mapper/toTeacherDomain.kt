package com.example.accountingofstudentretakesapp.domain.mapper

import com.example.accountingofstudentretakesapp.data.model.TeacherDto
import com.example.accountingofstudentretakesapp.domain.model.Teacher

fun TeacherDto.toTeacherDomain() = Teacher(
    userId = userId,
    fullName = fullName,
    disciplines = disciplines
)