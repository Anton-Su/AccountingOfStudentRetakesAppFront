package com.example.accountingofstudentretakesapp.data.local.mapper

import com.example.accountingofstudentretakesapp.data.local.entity.TeacherEntity
import com.example.accountingofstudentretakesapp.domain.model.Teacher

/** Конвертирует domain модель [Teacher] в Room entity [TeacherEntity]. */
fun Teacher.toTeacherEntity() = TeacherEntity(
    userId = userId,
    fullName = fullName,
    disciplines = disciplines.joinToString(separator = "||")
    //disciplines хранятся как строка с разделителем "||", т.к. Room не поддерживает List<String>
)
