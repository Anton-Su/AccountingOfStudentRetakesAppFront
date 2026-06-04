package com.example.accountingofstudentretakesapp.presentation.helpers

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun formatInstantToHuman(instant: Instant): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm").withZone(ZoneId.systemDefault())
        formatter.format(instant)
    } catch (_: Exception) {
        instant.toString()
    }
}