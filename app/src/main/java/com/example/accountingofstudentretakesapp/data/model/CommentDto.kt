package com.example.accountingofstudentretakesapp.data.model

import kotlinx.serialization.Serializable

/**
 * DTO комментария к пересдаче.
 * Приходит с сервера при получении списка комментариев.
 *
 * @property id уникальный идентификатор комментария
 * @property studentId идентификатор студента оставившего комментарий
 * @property studentFullName полное имя студента
 * @property subjectTitle название предмета пересдачи
 * @property groupName название группы студента
 * @property gradePlace оценка за место проведения
 * @property gradeTeacher оценка за преподавателя
 * @property gradeOverall общая оценка
 * @property comment текст комментария или null если не указан
 * @property retakeId идентификатор пересдачи к которой относится комментарий
 * @property retakeStartAt время начала пересдачи в формате ISO-8601
 * @property retakeEndAt время окончания пересдачи в формате ISO-8601
 */
@Serializable
data class CommentDto(
    val id: Long,
    val studentId: Long,
    val studentFullName: String,
    val subjectTitle: String,
    val groupName: String,
    val gradePlace: Int,
    val gradeTeacher: Int,
    val gradeOverall: Int,
    val comment: String?,
    val retakeId: Long,
    val retakeStartAt: String,
    val retakeEndAt: String,
)
