package com.example.accountingofstudentretakesapp.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.accountingofstudentretakesapp.R
import java.time.Instant
import java.time.ZoneId

/**
 * Диалоги выбора даты и времени.
 * Сначала открывается выбор даты, затем автоматически выбор времени.
 * Результат возвращается как [Instant] в московском времени.
 *
 * @param showDatePicker состояние видимости диалога выбора даты
 * @param onDateTimeSelected вызывается с выбранным моментом времени
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerDialogs(showDatePicker: MutableState<Boolean>,
                          onDateTimeSelected: (Instant) -> Unit)
{
    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState(is24Hour = true)
    val showTimePicker = remember { mutableStateOf(false) }
    if (showDatePicker.value) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker.value = false },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker.value = false
                    showTimePicker.value = true
                }) { Text(stringResource(R.string.dialog_ok)) }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker.value = false }) { Text(stringResource(R.string.dialog_cancel)) }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
    if (showTimePicker.value) {
        AlertDialog(
            onDismissRequest = { showTimePicker.value = false },
            confirmButton = {
                TextButton(onClick = {
                    val millis = datePickerState.selectedDateMillis ?: 0L
                    val instant = Instant.ofEpochMilli(millis)
                        .atZone(ZoneId.of("Europe/Moscow"))
                        .withHour(timePickerState.hour)
                        .withMinute(timePickerState.minute)
                        .withSecond(0)
                        .toInstant()
                    onDateTimeSelected(instant)  // отдаём Instant
                    showTimePicker.value = false
                }) { Text(stringResource(R.string.dialog_ok)) }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker.value = false }) { Text(stringResource(R.string.dialog_cancel)) }
            },
            title = { Text(stringResource(R.string.dialog_choose_time)) },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TimePicker(state = timePickerState)
                }
            }
        )
    }
}