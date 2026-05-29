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
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.example.accountingofstudentretakesapp.presentation.ui.component.RatingField
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentCommentScreen(
    retakeId: Long,
    uiState: RetakeUiState,
    onSubmit: (gradeplace: Int, gradeteacher: Int, gradeoverall: Int, comment: String) -> Unit,
    onBack: () -> Unit,
) {
    val gradePlace = remember { mutableStateOf("") }
    val gradeTeacher = remember { mutableStateOf("") }
    val gradeOverall = remember { mutableStateOf("") }
    val comment = remember { mutableStateOf(TextFieldValue()) }
    val errorMessage = remember { mutableStateOf<String?>(null) }

    fun parseIntOrNull(value: String): Int? = value.toIntOrNull()

    fun validate(): String? {
        val placeValue = parseIntOrNull(gradePlace.value)
        val teacherValue = parseIntOrNull(gradeTeacher.value)
        val overallValue = parseIntOrNull(gradeOverall.value)
        val commentText = comment.value.text.trim()
        return when {
            placeValue == null -> "Оцените аудиторию"
            placeValue !in 0..10 -> "Оценка за аудиторию должна быть от 0 до 10"
            teacherValue == null -> "Оцените преподавателя"
            teacherValue !in 0..10 -> "Оценка преподавателя должна быть от 0 до 10"
            overallValue == null -> "Введите общую оценку"
            overallValue !in 0..100 -> "Общая оценка должна быть от 0 до 100"
            commentText.length > 500 -> "Комментарий не должен быть длиннее 500 символов"
            else -> null
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Отзыв о пересдаче") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
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
            // Ошибки валидации
            val displayError = errorMessage.value ?: uiState.createCommentError
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
                        Text(
                            text = displayError,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            // Блок оценок
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
                    Text(
                        text = "Оценки",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    RatingField(
                        value = gradePlace.value,
                        onValueChange = { gradePlace.value = it.filter(Char::isDigit) },
                        label = "Аудитория",
                        range = "0–10",
                        icon = Icons.Outlined.LocationOn
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    RatingField(
                        value = gradeTeacher.value,
                        onValueChange = { gradeTeacher.value = it.filter(Char::isDigit) },
                        label = "Преподаватель",
                        range = "0–10",
                        icon = Icons.Outlined.Person
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    RatingField(
                        value = gradeOverall.value,
                        onValueChange = { gradeOverall.value = it.filter(Char::isDigit) },
                        label = "Общая оценка",
                        range = "0–100",
                        icon = Icons.Outlined.Star
                    )
                }
            }

            // Комментарий
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
                        Text(
                            text = "Комментарий",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    OutlinedTextField(
                        value = comment.value,
                        onValueChange = { comment.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Расскажите о своём опыте...") },
                        minLines = 4,
                        maxLines = 8,
                        shape = MaterialTheme.shapes.medium,
                        supportingText = {
                            Text(
                                text = "${comment.value.text.length}/500",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End,
                                style = MaterialTheme.typography.labelSmall,
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
                    val validationError = validate()
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
                } else {
//                    Icon(
//                        Icons.AutoMirrored.Outlined.Send,
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(18.dp)
//                            .padding(end = 4.dp)
//                    )
                }
                Text("Отправить отзыв", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}




