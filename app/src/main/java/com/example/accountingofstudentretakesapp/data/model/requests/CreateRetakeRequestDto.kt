package com.example.accountingofstudentretakesapp.data.model.requests

import kotlinx.serialization.Serializable

/**
 * DTO для создания пересдачи.
 * Отправляется на сервер при создании новой пересдачи.
 *
 * @property startAt время начала пересдачи в формате ISO-8601 (например "2024-01-01T10:00:00Z")
 * @property endAt время окончания пересдачи в формате ISO-8601
 * @property teacherIds список идентификаторов преподавателей ведущих пересдачу
 * @property subjectId идентификатор предмета пересдачи
 * @property type тип пересдачи (например "EXAM", "TEST")
 * @property place место проведения пересдачи
 * @property admission условие допуска к пересдаче или null если допуск не требуется
 */
@Serializable
data class CreateRetakeRequestDto(
    val startAt: String,
    val endAt: String,
    val teacherIds: List<Long>,
    val subjectId: Long,
    val type: String,
    val place: String,
    val admission: String? = null
)