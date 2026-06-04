package com.example.accountingofstudentretakesapp.presentation.helpers

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun formatIsoDateTimeToHuman(isoDateTime: String): String {
    return try {
        val instant = Instant.parse(isoDateTime)
        val formatter = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy, HH:mm"
        ).withZone(ZoneId.systemDefault())
        formatter.format(instant)
    } catch (_: Exception) {
        isoDateTime
    }
}