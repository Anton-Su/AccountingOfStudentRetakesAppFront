package com.example.accountingofstudentretakesapp.presentation.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.accountingofstudentretakesapp.domain.helpers.formatInstantToHuman
import java.time.Instant

/**
 * Поле ввода даты и времени только для чтения.
 * При нажатии на иконку календаря открывает диалог выбора даты и времени.
 *
 * @param value текущее значение даты и времени
 * @param label подпись поля
 * @param onDateTimePickerClick вызывается при нажатии на иконку календаря
 * @param modifier модификатор для настройки layout
 * @param readOnly если true — поле нельзя редактировать вручную
 */
@Composable
fun DateTimePickerField(value: Instant, label: String, onDateTimePickerClick: () -> Unit, modifier: Modifier = Modifier, readOnly: Boolean = true) {
    OutlinedTextField(
        value = formatInstantToHuman(value),
        onValueChange = { },
        label = { Text(label) },
        modifier = modifier,
        trailingIcon = {
            IconButton(onClick = onDateTimePickerClick) {
                Icon(
                    Icons.Filled.DateRange,
                    contentDescription = "Выбрать дату",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        readOnly = readOnly
    )
}





