package com.example.accountingofstudentretakesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность комментария в локальной базе данных.
 *
 * @property id уникальный идентификатор комментария
 * @property studentId идентификатор студента
 * @property studentFullName полное имя студента
 * @property subjectTitle название предмета
 * @property groupName название группы студента
 * @property gradePlace оценка за место проведения
 * @property gradeTeacher оценка за преподавателя
 * @property gradeOverall общая оценка
 * @property comment текст комментария или null если не указан
 * @property retakeId идентификатор пересдачи
 * @property retakeStartAt время начала пересдачи в формате строки
 * @property retakeEndAt время окончания пересдачи в формате строки
 */
@Entity(tableName = "comments")
data class CommentEntity(
    @PrimaryKey val id: Long,
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