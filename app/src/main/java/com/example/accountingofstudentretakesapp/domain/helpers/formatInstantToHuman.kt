package com.example.accountingofstudentretakesapp.domain.helpers

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Форматирует [Instant] в человекочитаемую строку по московскому времени.
 * При ошибке форматирования возвращает строковое представление [Instant].
 *
 * Пример результата: "01.01.2024, 10:00"
 *
 * @param instant момент времени для форматирования
 * @return отформатированная строка в формате "dd.MM.yyyy, HH:mm"
 */
fun formatInstantToHuman(instant: Instant): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm")
            .withZone(ZoneId.of("Europe/Moscow"))
        formatter.format(instant)
    } catch (_: Exception) {
        instant.toString()
    }
}