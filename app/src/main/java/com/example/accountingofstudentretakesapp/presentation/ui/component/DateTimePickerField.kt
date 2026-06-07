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
 * Компонент для выбора даты и времени
 * Отображает дату в удобном формате, но хранит ISO 8601 для сервера
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





