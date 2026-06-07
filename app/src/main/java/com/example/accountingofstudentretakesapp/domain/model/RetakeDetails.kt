package com.example.accountingofstudentretakesapp.domain.model

/**
 * Детальная информация о пересдаче.
 *
 * @property retake информация о пересдаче
 * @property enrollments список записавшихся студентов
 */
data class RetakeDetails(
    val retake: Retake,
    val enrollments: List<RetakeEnrollment>
)
