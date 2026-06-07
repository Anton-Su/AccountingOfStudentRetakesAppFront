package com.example.accountingofstudentretakesapp.domain.model

/**
 * Предмет.
 *
 * @property id уникальный идентификатор предмета
 * @property title название предмета
 */
data class Subject(
    val id: Long,
    val title: String
)