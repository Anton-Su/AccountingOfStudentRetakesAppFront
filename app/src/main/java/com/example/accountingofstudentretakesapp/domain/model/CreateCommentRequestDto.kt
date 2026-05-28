package com.example.accountingofstudentretakesapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateCommentRequestDto(
    val gradeplace: Int,
    val gradeteacher: Int,
    val gradeoverall: Int,
    val comment: String?,
    val retakeId: Long
)

