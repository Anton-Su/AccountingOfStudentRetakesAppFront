package com.example.accountingofstudentretakesapp.presentation.model

data class Comment(
    val id: Long,
    val studentId: Long,
    val gradePlace: Int,
    val gradeTeacher: Int,
    val gradeOverall: Int,
    val comment: String?,
    val retakeId: Long
)

