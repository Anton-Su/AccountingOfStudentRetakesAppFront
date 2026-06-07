package com.example.accountingofstudentretakesapp.domain.model.requests

data class CreateCommentRequest(
    val gradePlace: Int,
    val gradeTeacher: Int,
    val gradeOverall: Int,
    val comment: String?,
    val retakeId: Long
)