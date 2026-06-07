package com.example.accountingofstudentretakesapp.domain.model

/**
 * Долг студента по предмету.
 *
 * @property id уникальный идентификатор долга
 * @property subjectId идентификатор предмета
 * @property subjectTitle название предмета
 */
data class StudentDebt(
    val id: Long,
    val subjectId: Long,
    val subjectTitle: String,
)