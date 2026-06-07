package com.example.accountingofstudentretakesapp.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.accountingofstudentretakesapp.R
import com.example.accountingofstudentretakesapp.data.remote.SettingsDataStore
import com.example.accountingofstudentretakesapp.domain.helpers.makeFIO
import com.example.accountingofstudentretakesapp.presentation.ui.component.CircularPercentageIndicator
import com.example.accountingofstudentretakesapp.presentation.ui.component.RetakeInfoCard
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState
import java.time.Instant

/**
 * Главный экран студента.
 *
 * В TopAppBar показывает ФИО студента из DataStore.
 * Данные загружаются один раз при открытии экрана через [onLoadStudentData].
 *
 * Содержит секции:
 *
 * **Место в топе должников** — карточка с [CircularPercentageIndicator],
 * показывает место среди всех студентов. Скрыта если данных нет.
 *
 * **Долги** — список предметов со статусом DEBT (красные карточки).
 * Состояния: загрузка / ошибка / пусто / список.
 *
 * **Доступные пересдачи** — пересдачи по предметам из долгов,
 * на которые студент ещё не записан. Кнопка "+" для записи.
 * Фильтруется по [RetakeUiState.availableRetakes] и [RetakeUiState.studentDebts].
 *
 * **Я записан на...** — активные пересдачи (endAt > сейчас).
 * Кнопка отмены заблокирована если пересдача уже началась.
 *
 * **Прошедшие пересдачи** — пересдачи где endAt <= сейчас.
 * Кнопка перехода на экран отзыва.
 *
 * @param uiState общий UI стейт
 * @param onLoadStudentData загрузить все данные студента
 * @param onRetakeClick перейти на экран отзыва по ID пересдачи
 * @param onEnrollRetake записаться на пересдачу (subjectId, retakeId)
 * @param onCancelRetake отменить запись на пересдачу (subjectId, retakeId)
 * @param onLogout выйти из аккаунта
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentHomeScreen(uiState: RetakeUiState,
                      onLoadStudentData: () -> Unit,
                      onRetakeClick: (Long) -> Unit,
                      onEnrollRetake: (Long, Long) -> Unit,
                      onCancelRetake: (Long, Long) -> Unit,
                      onLogout: () -> Unit)
{
    LaunchedEffect(Unit) {
        onLoadStudentData()
    }
    val context = LocalContext.current
    val settings = SettingsDataStore(context)
    val firstName by settings.firstNameFlow.collectAsState(initial = "")
    val lastName by settings.lastNameFlow.collectAsState(initial = "")
    val secondName by settings.secondNameFlow.collectAsState(initial = "")
    val formattedName = makeFIO(firstName, secondName, lastName)
    val availableForDebts = remember(uiState.availableRetakes, uiState.studentDebts) {
        uiState.availableRetakes.filter { retake ->
            uiState.studentDebts.any { debt -> debt.subjectId == retake.subjectId }
        }
    }
    val enrolledActive = remember(uiState.enrolledRetakes) {
        uiState.enrolledRetakes.filter { retake ->
            retake.endAt > Instant.now()
        }
    }

    val enrolledPast = remember(uiState.enrolledRetakes) {
        uiState.enrolledRetakes.filter { retake ->
            retake.endAt <= Instant.now()
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(stringResource(R.string.student_home_title))
                        if (formattedName.isNotBlank()) {
                            Text(formattedName, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = stringResource(R.string.logout))
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                uiState.studentDebtRank?.let { rank ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = stringResource(R.string.top_place_label), style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = stringResource(R.string.student_rank_value, rank.place, rank.totalStudents), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                                CircularPercentageIndicator(
                                    percentage = rank.topPercent,
                                    size = 100.dp,
                                    strokeWidth = 5.dp,
                                    progressColor = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
            item {
                Text(stringResource(R.string.debt_section), style = MaterialTheme.typography.titleMedium)
            }
            when {
                uiState.studentDebtsLoading -> item {
                    Text(stringResource(R.string.loading_debts), style = MaterialTheme.typography.bodyMedium)
                }
                uiState.studentDebtsError != null -> item {
                    Text(text = uiState.studentDebtsError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
                }
                uiState.studentDebts.isEmpty() -> item {
                    Text(stringResource(R.string.no_debts), style = MaterialTheme.typography.bodyMedium)
                }
                else -> {
                    // key = { "debt-${it.id}"
                    items(uiState.studentDebts) { debt ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.errorContainer
                            )
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(debt.subjectTitle, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                    item {
                        Text(stringResource(R.string.available_retakes), style = MaterialTheme.typography.titleMedium)
                    }
                    if (uiState.availableRetakesLoading) {
                        item { Text(stringResource(R.string.loading_available_retakes), style = MaterialTheme.typography.bodyMedium) }
                    } else if (uiState.availableRetakesError != null) {
                        item {
                            Text(text = uiState.availableRetakesError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
                        }
                    } else if (availableForDebts.isEmpty()) {
                        item { Text(stringResource(R.string.no_available_retakes), style = MaterialTheme.typography.bodyMedium) }
                    } else {
                        // key = { "available-${it.id}" }
                        items(availableForDebts) { retake ->
                            val matchingDebt = uiState.studentDebts.find { it.subjectId == retake.subjectId }
                            if (matchingDebt != null) {
                                RetakeInfoCard(
                                    subjectTitle = matchingDebt.subjectTitle,
                                    place = retake.place,
                                    startAt = retake.startAt,
                                    endAt = retake.endAt,
                                    type = retake.type,
                                    admission = retake.admission,
                                    actionIcon = Icons.Filled.Add,
                                    actionDescription = stringResource(R.string.enroll_action),
                                    onAction = { onEnrollRetake(matchingDebt.subjectId, retake.id) }
                                )
                            }
                        }
                    }
                    item { Text(stringResource(R.string.enrolled_retakes), style = MaterialTheme.typography.titleMedium) }
                    if (uiState.enrolledRetakesLoading)
                        item { Text(stringResource(R.string.loading_enrolled), style = MaterialTheme.typography.bodyMedium) }
                    else if (uiState.enrolledRetakesError != null) {
                        item {
                            Text(text = uiState.enrolledRetakesError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
                        }
                    } else if (enrolledActive.isEmpty()) {
                        item { Text(stringResource(R.string.no_enrolled), style = MaterialTheme.typography.bodyMedium) }
                    } else {
                        //, key = { "enrolled-${it.id}" }
                        items(enrolledActive) { retake ->
                            val matchingDebt = uiState.studentDebts.find { it.subjectId == retake.subjectId }
                            if (matchingDebt != null) {
                                RetakeInfoCard(
                                    subjectTitle = matchingDebt.subjectTitle,
                                    place = retake.place,
                                    startAt = retake.startAt,
                                    endAt = retake.endAt,
                                    type = retake.type,
                                    admission = retake.admission,
                                    actionIcon = Icons.Filled.Close,
                                    actionDescription = stringResource(R.string.cancel_action),
                                    actionEnabled = retake.startAt > Instant.now(),
                                    onAction = { onCancelRetake(matchingDebt.subjectId, retake.id) },
                                )
                            }
                        }
                    }
                }
            }
            item { Text(stringResource(R.string.past_retakes), style = MaterialTheme.typography.titleMedium) }
            items(enrolledPast) { retake ->
                val subjectDebt = uiState.subjects.find { it.id == retake.subjectId }
                if (subjectDebt != null) {
                    RetakeInfoCard(
                        subjectTitle = subjectDebt.title,
                        place = retake.place,
                        startAt = retake.startAt,
                        endAt = retake.endAt,
                        type = retake.type,
                        admission = retake.admission,
                        actionIcon = Icons.Filled.Email,  // иконка для комментария
                        actionDescription = stringResource(R.string.comment_action),
                        onAction = { onRetakeClick(retake.id) }  // открыть экран с комментарием
                    )
                }
            }
        }

    }
}