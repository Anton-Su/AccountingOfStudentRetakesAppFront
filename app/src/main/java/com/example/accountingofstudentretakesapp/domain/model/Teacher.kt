package com.example.accountingofstudentretakesapp.domain.model

/**
 * Преподаватель.
 *
 * @property userId уникальный идентификатор преподавателя
 * @property fullName полное имя преподавателя
 * @property disciplines список дисциплин преподавателя
 */
data class Teacher(
    val userId: Long,
    val fullName: String,
    val disciplines: List<String>
)