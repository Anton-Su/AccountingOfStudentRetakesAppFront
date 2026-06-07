package com.example.accountingofstudentretakesapp.data.model

import kotlinx.serialization.Serializable

/**
 * DTO пересдачи.
 * Приходит с сервера при получении списка пересдач.
 *
 * @property id уникальный идентификатор пересдачи
 * @property type тип пересдачи (например "EXAM", "TEST")
 * @property place место проведения
 * @property startAt время начала в формате ISO-8601
 * @property endAt время окончания в формате ISO-8601
 * @property lastModified время последнего изменения в формате ISO-8601
 * @property teacherIds список идентификаторов преподавателей
 * @property subjectId идентификатор предмета
 * @property admission условие допуска или null если не требуется
 */
@Serializable
data class RetakeDto(
    val id: Long,
    val type: String,
    val place: String,
    val startAt: String,
    val endAt: String,
    val lastModified: String,
    val teacherIds: List<Long>,
    val subjectId: Long,
    val admission: String? = null,
)