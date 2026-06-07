package com.example.accountingofstudentretakesapp.data.model.requests

import kotlinx.serialization.Serializable

/**
 * DTO для создания комментария к пересдаче.
 * Отправляется на сервер при создании нового комментария.
 *
 * @property gradePlace оценка за место проведения
 * @property gradeTeacher оценка за преподавателя
 * @property gradeOverall общая оценка
 * @property comment текст комментария или null если не указан
 * @property retakeId идентификатор пересдачи к которой относится комментарий
 */
@Serializable
data class CreateCommentRequestDto(
    val gradePlace: Int,
    val gradeTeacher: Int,
    val gradeOverall: Int,
    val comment: String?,
    val retakeId: Long
)