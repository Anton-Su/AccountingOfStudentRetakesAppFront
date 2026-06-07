package com.example.accountingofstudentretakesapp.data.model

import kotlinx.serialization.Serializable

/**
 * DTO детальной информации о пересдаче.
 * Приходит с сервера при запросе деталей конкретной пересдачи.
 *
 * @property retake информация о пересдаче
 * @property enrollments список записавшихся студентов
 */
@Serializable
data class RetakeDetailsDto(
    val retake: RetakeDto,
    val enrollments: List<RetakeEnrollmentDto>
)
