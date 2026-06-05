package com.example.accountingofstudentretakesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Entity для хранения комментариев студентов о пересдачах.
 * Данные используются для кеширования комментариев локально.
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