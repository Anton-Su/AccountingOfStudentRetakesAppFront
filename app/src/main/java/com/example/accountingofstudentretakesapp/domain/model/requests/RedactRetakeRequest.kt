package com.example.accountingofstudentretakesapp.domain.model.requests

import java.time.Instant

/**
 * Запрос на редактирование существующей пересдачи.
 * Отличается от [CreateRetakeRequest] наличием идентификатора пересдачи.
 *
 * @property id идентификатор редактируемой пересдачи
 * @property startAt время начала пересдачи
 * @property endAt время окончания пересдачи
 * @property teacherIds список идентификаторов преподавателей
 * @property subjectId идентификатор предмета
 * @property type тип пересдачи (например "EXAM", "TEST")
 * @property place место проведения
 * @property admission условие допуска или null если не требуется
 */
data class RedactRetakeRequest(
    val id: Long,
    val startAt: Instant,
    val endAt: Instant,
    val teacherIds: List<Long>,
    val subjectId: Long,
    val type: String,
    val place: String,
    val admission: String? = null
)