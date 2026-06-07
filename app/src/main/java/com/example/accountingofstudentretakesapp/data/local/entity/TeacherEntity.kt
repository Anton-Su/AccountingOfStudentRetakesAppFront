package com.example.accountingofstudentretakesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Сущность преподавателя в локальной базе данных.
 *
 * @property userId уникальный идентификатор преподавателя
 * @property fullName полное имя преподавателя
 * @property disciplines список дисциплин преподавателя,
 * хранится как строка с разделителем "||" (например "Математика||Физика")
 */

@Entity(tableName = "teachers")
data class TeacherEntity(
    @PrimaryKey val userId: Long,
    val fullName: String,
    val disciplines: String
)