package com.example.accountingofstudentretakesapp.domain.model

/**
 * Рейтинг студента по количеству долгов.
 *
 * @property studentId идентификатор студента
 * @property debtsCount количество долгов студента
 * @property place место студента в рейтинге
 * @property totalStudents общее количество студентов в рейтинге
 * @property topPercent процент студентов у которых долгов больше
 */
data class StudentDebtRank(
    val studentId: Long,
    val debtsCount: Int,
    val place: Int,
    val totalStudents: Int,
    val topPercent: Int
)