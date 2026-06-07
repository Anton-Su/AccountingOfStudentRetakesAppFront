package com.example.accountingofstudentretakesapp.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.accountingofstudentretakesapp.R
import com.example.accountingofstudentretakesapp.domain.helpers.formatInstantToHuman
import java.time.Instant

/**
 * Карточка пересдачи с кнопкой действия.
 *
 * Отображает информацию о пересдаче и кнопку для выполнения действия
 * (записаться или отменить запись).
 *
 * Содержит:
 * - Название предмета
 * - Место проведения (с иконкой)
 * - Время начала и конца (с иконкой)
 * - Тип пересдачи (с иконкой)
 * - Допуск — если указан (с иконкой)
 * - Кнопка действия справа
 *
 * @param subjectTitle название предмета
 * @param place место проведения
 * @param startAt время начала пересдачи
 * @param endAt время конца пересдачи
 * @param type тип пересдачи (например "Экзамен")
 * @param admission требования допуска — если null, не отображается
 * @param actionIcon иконка кнопки действия
 * @param actionDescription описание действия для accessibility
 * @param onAction колбэк нажатия на кнопку действия
 * @param actionEnabled активна ли кнопка действия (по умолчанию true)
 * @param modifier модификатор карточки
 */
@Composable
fun RetakeInfoCard(subjectTitle: String, place: String, startAt: Instant, endAt: Instant, type: String, admission: String?, actionIcon: ImageVector, actionDescription: String,
                   onAction: () -> Unit,
                   actionEnabled: Boolean = true,
                   modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(subjectTitle, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(Icons.Outlined.LocationOn, contentDescription = null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(place, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(Icons.Outlined.DateRange, contentDescription = null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(stringResource(R.string.retake_time_range, formatInstantToHuman(startAt), formatInstantToHuman(endAt)), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(Icons.Outlined.Info, contentDescription = null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(type, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                admission?.let {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(Icons.Outlined.CheckCircle, contentDescription = null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.primary)
                        Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
            IconButton(onClick = onAction, enabled = actionEnabled) {
                Icon(actionIcon, contentDescription = actionDescription)
            }
        }
    }
}