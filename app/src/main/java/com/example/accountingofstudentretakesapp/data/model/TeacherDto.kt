package com.example.accountingofstudentretakesapp.data.model
import kotlinx.serialization.Serializable

/**
 * DTO преподавателя.
 *
 * @property userId уникальный идентификатор преподавателя
 * @property fullName полное имя преподавателя
 * @property disciplines список дисциплин преподавателя
 */
@Serializable
data class TeacherDto(
    val userId: Long,
    val fullName: String,
    val disciplines: List<String>

)

