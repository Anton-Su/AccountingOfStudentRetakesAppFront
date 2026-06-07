package com.example.accountingofstudentretakesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateCommentRequestDto(
    val gradePlace: Int,
    val gradeTeacher: Int,
    val gradeOverall: Int,
    val comment: String?,
    val retakeId: Long
)
