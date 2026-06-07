package com.example.accountingofstudentretakesapp.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RetakeFormScreen(title: String, uiState: RetakeUiState, initialType: String? = null, initialPlace: String = "", initialStartAt: Instant = Instant.now(), initialEndAt: Instant = Instant.now().plusSeconds(90 * 60), initialAdmission: String = "", initialSubjectId: Long? = null, initialTeacherIds: List<Long> = emptyList(), isLoading: Boolean = false, submitButtonText: String,
                     onLoadSubjects: () -> Unit,
                     onLoadTeachers: (String) -> Unit,
                     onClearTeachers: () -> Unit,
                     onSubmit: (CreateRetakeRequest) -> Unit,
                     onBack: () -> Unit
) {
    val type = remember { mutableStateOf(initialType) }
    val place = remember { mutableStateOf(initialPlace) }
    val startAt = remember { mutableStateOf(initialStartAt) }
    val endAt = remember { mutableStateOf(initialEndAt) }
    val admission = remember { mutableStateOf(initialAdmission) }
    val selectedSubject = remember { mutableStateOf(initialSubjectId) }
    val selectedTeachers = remember { mutableStateListOf<Long>().also { it.addAll(initialTeacherIds) } }
    val expandedSubject = remember { mutableStateOf(false) }
    val showStartDateTimePicker = remember { mutableStateOf(false) }
    val showEndDateTimePicker = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        onLoadSubjects()
    }
    LaunchedEffect(uiState.subjects) {
        if (initialSubjectId != null && uiState.subjects.isNotEmpty()) {
            val subjectTitle = uiState.subjects.find { it.id == initialSubjectId }?.title
            if (subjectTitle != null) {
                onLoadTeachers(subjectTitle)
            }
        }
    }
    LaunchedEffect(uiState.teachersByDiscipline) {
        if (uiState.teachersByDiscipline.isNotEmpty()) {
            selectedTeachers.clear()
            selectedTeachers.addAll(
                initialTeacherIds.filter { id ->
                    uiState.teachersByDiscipline.any { it.userId == id }
                }
            )
        }
    }
    // очищаем преподавателей при уходе с экрана
    DisposableEffect(Unit) {
        onDispose { onClearTeachers() }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                val error = uiState.createRetakeError ?: uiState.redactRetakeError
                if (error != null) {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(text = error, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(12.dp))
                    }
                }
            }
            item {
                OutlinedTextField(
                    value = place.value,
                    onValueChange = { place.value = it },
                    label = { Text("Место проведения") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text(text = "Выберите тип пересдачи", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(vertical = 8.dp))
                RetakeTypeSelector(
                    selectedType = type.value,
                    onTypeSelected = { type.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                DateTimePickerField(
                    value = startAt.value,
                    label = "Время начала",
                    onDateTimePickerClick = { showStartDateTimePicker.value = true },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                DateTimePickerField(
                    value = endAt.value,
                    label = "Время конца",
                    onDateTimePickerClick = { showEndDateTimePicker.value = true },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                OutlinedTextField(
                    value = admission.value,
                    onValueChange = { admission.value = it },
                    label = { Text("Допуск (опционально)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text(text = "Выберите предмет", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(vertical = 8.dp))
                Button(
                    onClick = { expandedSubject.value = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(selectedSubject.value?.let { id -> uiState.subjects.find { it.id == id }?.title } ?: "Предмет не выбран")
                }
                DropdownMenu(
                    expanded = expandedSubject.value,
                    onDismissRequest = { expandedSubject.value = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    uiState.subjects.forEach { subject ->
                        DropdownMenuItem(
                            text = { Text(subject.title) },
                            onClick = {
                                selectedSubject.value = subject.id
                                expandedSubject.value = false
                                selectedTeachers.clear()
                                onLoadTeachers(subject.title)
                            }
                        )
                    }
                }
            }
            item {
                Text(
                    text = "Выберите преподавателей",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                when {
                    uiState.teachersByDisciplineLoading -> {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                    uiState.teachersByDiscipline.isEmpty() -> {
                        Text("Нет преподавателей по этому предмету")
                    }
                    else -> {
                        Card {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {
                                uiState.teachersByDiscipline.forEach { teacher ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(text = teacher.fullName)
                                            Text(text = teacher.disciplines.joinToString("; "), style = MaterialTheme.typography.bodySmall)
                                        }
                                        Checkbox(
                                            checked = selectedTeachers.contains(teacher.userId),
                                            onCheckedChange = { checked ->
                                                if (checked) selectedTeachers.add(teacher.userId)
                                                else selectedTeachers.remove(teacher.userId)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = onBack, modifier = Modifier.weight(1f)) {
                        Text("Отмена")
                    }
                    // Log.e("RetakeFormScreen", "Submitting with startAt=${startAt.value}, endAt=${endAt.value}, teachers=$selectedTeachers, subject=${selectedSubject.value}, type=${type.value}, place=${place.value}, admission=${admission.value}")
                    Button(
                        onClick = {
                            onSubmit(
                                CreateRetakeRequest(
                                    startAt = startAt.value,
                                    endAt = endAt.value,
                                    teacherIds = selectedTeachers.toList(),
                                    subjectId = selectedSubject.value!!,
                                    type = type.value!!,
                                    place = place.value,
                                    admission = admission.value.ifEmpty { null }
                                )
                            )
                        },
                        enabled = !isLoading && selectedSubject.value != null && type.value != null && selectedTeachers.isNotEmpty(),
                        modifier = Modifier.weight(1f)
                    ) {
                        if (isLoading) CircularProgressIndicator(modifier = Modifier.padding(end = 8.dp))
                        Text(submitButtonText)
                    }
                }
            }
        }
    }
    DateTimePickerDialogs(
        showDatePicker = showStartDateTimePicker,
        onDateTimeSelected = { startAt.value = it }
    )
    DateTimePickerDialogs(
        showDatePicker = showEndDateTimePicker,
        onDateTimeSelected = { endAt.value = it }
    )
}