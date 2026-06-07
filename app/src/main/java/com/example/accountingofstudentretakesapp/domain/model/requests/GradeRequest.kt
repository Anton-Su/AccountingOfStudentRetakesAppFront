package com.example.accountingofstudentretakesapp.domain.model.requests

/**
 * Запрос на выставление оценки студенту.
 *
 * @property score оценка студента
 */
data class GradeRequest(
    val score: Int
)
