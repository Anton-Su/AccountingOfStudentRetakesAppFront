package com.example.accountingofstudentretakesapp.data.model

import kotlinx.serialization.Serializable

/**
 * DTO предмета.
 *
 * @property id уникальный идентификатор предмета
 * @property title название предмета
 */
@Serializable
data class SubjectDto(
    val id: Long,
    val title: String
)
