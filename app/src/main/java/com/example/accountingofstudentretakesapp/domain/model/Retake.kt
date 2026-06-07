package com.example.accountingofstudentretakesapp.domain.model

import java.time.Instant

/**
 * Пересдача.
 *
 * @property id уникальный идентификатор пересдачи
 * @property type тип пересдачи (например "EXAM", "TEST")
 * @property subjectId идентификатор предмета
 * @property place место проведения
 * @property admission условие допуска или null если не требуется
 * @property startAt время начала пересдачи
 * @property endAt время окончания пересдачи
 * @property lastModified время последнего изменения
 * @property teacherIds список идентификаторов преподавателей
 */
data class Retake(
    val id: Long,
    val type: String,
    val subjectId: Long,
    val place: String,
    val admission: String?,
    val startAt: Instant,
    val endAt: Instant,
    val lastModified: Instant,
    val teacherIds: List<Long>
)