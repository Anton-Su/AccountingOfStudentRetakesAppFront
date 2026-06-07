package com.example.accountingofstudentretakesapp.domain.model.requests

/**
 * Запрос на создание комментария к пересдаче.
 *
 * @property gradePlace оценка за место проведения (0-10)
 * @property gradeTeacher оценка за преподавателя (0-10)
 * @property gradeOverall общая оценка (0-100)
 * @property comment текст комментария или null если не указан
 * @property retakeId идентификатор пересдачи
 */
data class CreateCommentRequest(
    val gradePlace: Int,
    val gradeTeacher: Int,
    val gradeOverall: Int,
    val comment: String?,
    val retakeId: Long
)