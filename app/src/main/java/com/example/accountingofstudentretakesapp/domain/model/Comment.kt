package com.example.accountingofstudentretakesapp.domain.model

/**
 * Комментарий студента к пересдаче.
 *
 * @property id уникальный идентификатор комментария
 * @property studentId идентификатор студента
 * @property studentFullName полное имя студента
 * @property subjectTitle название предмета
 * @property groupName название группы студента
 * @property gradePlace оценка за место проведения (0-10)
 * @property gradeTeacher оценка за преподавателя (0-10)
 * @property gradeOverall общая оценка (0-100)
 * @property comment текст комментария или null если не указан
 * @property retakeId идентификатор пересдачи
 * @property retakeStartAt время начала пересдачи в формате ISO-8601
 * @property retakeEndAt время окончания пересдачи в формате ISO-8601
 */
data class Comment(
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