package com.example.accountingofstudentretakesapp.data.model

import kotlinx.serialization.Serializable

/**
 * DTO рейтинга студента по количеству долгов.
 *
 * @property studentId идентификатор студента
 * @property debtsCount количество долгов студента
 * @property place место студента в рейтинге
 * @property totalStudents общее количество студентов в рейтинге
 * @property topPercent процент студентов у которых долгов больше
 */
@Serializable
data class StudentDebtRankDto(
    val studentId: Long,
    val debtsCount: Int,
    val place: Int,
    val totalStudents: Int,
    val topPercent: Int
)