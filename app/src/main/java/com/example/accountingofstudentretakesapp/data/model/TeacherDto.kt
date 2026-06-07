package com.example.accountingofstudentretakesapp.data.model
import kotlinx.serialization.Serializable

@Serializable
data class TeacherDto(
    val userId: Long,
    val fullName: String,
    val disciplines: List<String>

)

