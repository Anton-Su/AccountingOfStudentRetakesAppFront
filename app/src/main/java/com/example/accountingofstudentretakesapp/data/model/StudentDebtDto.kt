package com.example.accountingofstudentretakesapp.data.model

import kotlinx.serialization.Serializable

/**
 * DTO долга студента по предмету.
 *
 * @property id уникальный идентификатор долга
 * @property subjectId идентификатор предмета
 * @property subjectTitle название предмета
 */
@Serializable
data class StudentDebtDto(
    val id: Long,
    val subjectId: Long,
    val subjectTitle: String,
)
