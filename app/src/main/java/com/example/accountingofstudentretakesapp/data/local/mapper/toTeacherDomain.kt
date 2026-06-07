package com.example.accountingofstudentretakesapp.data.local.mapper

import com.example.accountingofstudentretakesapp.data.local.entity.TeacherEntity
import com.example.accountingofstudentretakesapp.domain.model.Teacher

/** Конвертирует Room entity [TeacherEntity] в domain модель [Teacher]. */
fun TeacherEntity.toTeacherDomain() = Teacher(
    userId = userId,
    fullName = fullName,
    disciplines = disciplines.split("||").filter { it.isNotEmpty() }
    //disciplines хранятся как строка с разделителем "||", т.к. Room не поддерживает List<String>
)