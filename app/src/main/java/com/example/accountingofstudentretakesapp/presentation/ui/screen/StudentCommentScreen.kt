package com.example.accountingofstudentretakesapp.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.example.accountingofstudentretakesapp.R
import com.example.accountingofstudentretakesapp.domain.helpers.ValidationError
import com.example.accountingofstudentretakesapp.domain.helpers.validate
import com.example.accountingofstudentretakesapp.presentation.ui.component.RatingField
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState

/**
 * Экран отправки отзыва студента о пересдаче.
 *
 * Содержит три поля оценок и текстовый комментарий:
 * - Аудитория (0–10) — [RatingField]
 * - Преподаватель (0–10) — [RatingField]
 * - Общая оценка (0–100) — [RatingField]
 * - Комментарий (до 500 символов) с счётчиком символов
 *
 * Валидация выполняется локально перед отправкой через [validate].
 * Ошибка отображается в карточке вверху экрана — как локальная
 * так и серверная ([RetakeUiState.createCommentError]).
 *
 * Кнопка "Отправить отзыв" заблокирована во время загрузки.
 *
 * @param uiState UI стейт — состояние загрузки и ошибка с сервера
 * @param onSubmit колбэк отправки отзыва с оценками и комментарием
 * @param onBack вернуться назад
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentCommentScreen(
    uiState: RetakeUiState,
    onSubmit: (gradePlace: Int, gradeTeacher: Int, gradeOverall: Int, comment: String) -> Unit,
    onBack: () -> Unit,
) {
    val gradePlace = remember { mutableStateOf("") }
    val gradeTeacher = remember { mutableStateOf("") }
    val gradeOverall = remember { mutableStateOf("") }
    val comment = remember { mutableStateOf(TextFieldValue()) }
    val errorMessage = remember { mutableStateOf<ValidationError?>(null) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.student_comment_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val displayError = errorMessage.value?.let {
                when (it) {
                    ValidationError.PLACE_REQUIRED -> stringResource(R.string.validation_rate_place)
                    ValidationError.PLACE_RANGE -> stringResource(R.string.validation_place_range)
                    ValidationError.TEACHER_REQUIRED -> stringResource(R.string.validation_rate_teacher)
                    ValidationError.TEACHER_RANGE -> stringResource(R.string.validation_teacher_range)
                    ValidationError.OVERALL_REQUIRED -> stringResource(R.string.validation_overall_required)
                    ValidationError.OVERALL_RANGE -> stringResource(R.string.validation_overall_range)
                    ValidationError.COMMENT_LENGTH -> stringResource(R.string.validation_comment_length)
                }
            } ?: uiState.createCommentError
            if (displayError != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Outlined.Warning,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(text = displayError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(text = stringResource(R.string.student_comment_ratings), style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    RatingField(
                        value = gradePlace.value,
                        onValueChange = { gradePlace.value = it.filter { char -> char.isDigit() } },
                        label = stringResource(R.string.student_comment_place),
                        range = "0–10",
                        icon = Icons.Outlined.LocationOn
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    RatingField(
                        value = gradeTeacher.value,
                        onValueChange = { gradeTeacher.value = it.filter {char -> char.isDigit()} },
                        label = stringResource(R.string.student_comment_teacher),
                        range = "0–10",
                        icon = Icons.Outlined.Person
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    RatingField(
                        value = gradeOverall.value,
                        onValueChange = { gradeOverall.value = it.filter {char -> char.isDigit()} },
                        label = stringResource(R.string.student_comment_overall),
                        range = "0–100",
                        icon = Icons.Outlined.Star
                    )
                }
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Outlined.Edit,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(text = stringResource(R.string.student_comment_comment), style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    OutlinedTextField(
                        value = comment.value,
                        onValueChange = { comment.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(stringResource(R.string.student_comment_placeholder)) },
                        minLines = 4,
                        maxLines = 8,
                        shape = MaterialTheme.shapes.medium,
                        supportingText = {
                            Text(text = stringResource(R.string.student_comment_counter, comment.value.text.length), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End, style = MaterialTheme.typography.labelSmall,
                                color = if (comment.value.text.length > 500)
                                    MaterialTheme.colorScheme.error
                                else
                                    MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    )
                }
            }
            Button(
                onClick = {
                    val validationError = validate(gradePlace.value, gradeTeacher.value, gradeOverall.value, comment.value.text)
                    if (validationError != null) {
                        errorMessage.value = validationError
                        return@Button
                    }
                    errorMessage.value = null
                    onSubmit(
                        gradePlace.value.toInt(),
                        gradeTeacher.value.toInt(),
                        gradeOverall.value.toInt(),
                        comment.value.text.trim()
                    )
                },
                enabled = !uiState.createCommentLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = MaterialTheme.shapes.large
            ) {
                if (uiState.createCommentLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(18.dp)
                            .padding(end = 8.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Text(stringResource(R.string.student_comment_submit), style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}