package com.example.accountingofstudentretakesapp.domain.model

import com.example.accountingofstudentretakesapp.presentation.model.RetakeEnrollment
import kotlinx.serialization.Serializable

@Serializable
data class RetakeEnrollmentDto(
    val id: Long,
    val retakeId: Long,
    val studentId: Long,
    val studentSubjectId: Long,
    val studentFullName: String,
    val groupName: String
)

fun RetakeEnrollment.toEnrollmentDto(): RetakeEnrollmentDto = RetakeEnrollmentDto(
    id = this.id,
    retakeId = this.retakeId,
    studentId = this.studentId,
    studentSubjectId = this.studentSubjectId,
    studentFullName = this.studentFullName,
    groupName = this.groupName
)