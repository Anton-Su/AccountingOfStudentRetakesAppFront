package com.example.accountingofstudentretakesapp.data.model.requests

import kotlinx.serialization.Serializable

/**
 * DTO для выставления оценки студенту.
 * Отправляется на сервер при оценивании студента на пересдаче.
 *
 * @property score оценка студента
 */
@Serializable
data class GradeRequestDto(
    val score: Int
)