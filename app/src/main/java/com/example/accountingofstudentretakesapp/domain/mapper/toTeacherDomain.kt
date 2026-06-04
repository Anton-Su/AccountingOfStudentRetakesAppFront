package com.example.accountingofstudentretakesapp.domain.mapper

import com.example.accountingofstudentretakesapp.domain.model.TeacherDto
import com.example.accountingofstudentretakesapp.presentation.model.Teacher

fun TeacherDto.toTeacherDomain() = Teacher(
    userId = userId,
    fullName = fullName,
    disciplines = disciplines
)