package com.example.accountingofstudentretakesapp.presentation.ui.component

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

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
fun formatDateTimeToIso(dateMillis: Long?, hour: Int, minute: Int): String {
    if (dateMillis == null) return ""
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = dateMillis
    calendar.set(Calendar.HOUR_OF_DAY, hour)
    calendar.set(Calendar.MINUTE, minute)
    calendar.set(Calendar.SECOND, 0)
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    return format.format(calendar.time)
}

