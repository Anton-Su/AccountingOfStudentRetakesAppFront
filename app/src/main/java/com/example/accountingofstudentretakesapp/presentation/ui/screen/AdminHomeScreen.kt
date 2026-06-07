package com.example.accountingofstudentretakesapp.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.accountingofstudentretakesapp.R
import com.example.accountingofstudentretakesapp.data.remote.SettingsDataStore
import com.example.accountingofstudentretakesapp.domain.helpers.formatInstantToHuman
import com.example.accountingofstudentretakesapp.domain.helpers.makeFIO
import com.example.accountingofstudentretakesapp.presentation.ui.component.RetakeCommentsCard
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState
import java.time.Instant

/**
 * Главный экран администратора.
 *
 * Отображает список всех пересдач с комментариями студентов.
 * В TopAppBar показывает ФИО администратора из DataStore.
 *
 * Каждая карточка пересдачи содержит:
 * - Название предмета и тип (первые 3 символа)
 * - Место, время начала и конца
 * - Кнопку редактирования — только если пересдача ещё не началась
 * - Кнопку удаления — всегда
 * - [RetakeCommentsCard] с отзывами студентов
 *
 * Состояния загрузки:
 * - Загрузка → текст "Загрузка пересдач..."
 * - Ошибка → текст ошибки красным
 * - Пусто → текст "Нет пересдач"
 * - Успех → [LazyColumn] с карточками
 *
 * @param uiState общий UI стейт
 * @param onLoadRetakes загрузить все пересдачи
 * @param onLoadComments загрузить все комментарии
 * @param onAddRetake перейти на экран создания пересдачи
 * @param onEditRetake перейти на экран редактирования пересдачи по ID
 * @param onDeleteRetake удалить пересдачу по ID
 * @param onLogout выйти из аккаунта
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminHomeScreen(uiState: RetakeUiState,
    onLoadRetakes: () -> Unit,
    onLoadComments: () -> Unit,
    onAddRetake: () -> Unit,
    onEditRetake: (Long) -> Unit,
    onDeleteRetake: (Long) -> Unit,
    onLogout: () -> Unit
) {
    LaunchedEffect(Unit) {
        onLoadRetakes()
        onLoadComments()
    }
    val context = LocalContext.current
    val settings = SettingsDataStore(context)
    val firstName by settings.firstNameFlow.collectAsState(initial = "")
    val lastName by settings.lastNameFlow.collectAsState(initial = "")
    val secondName by settings.secondNameFlow.collectAsState(initial = "")
    val formattedName = makeFIO(firstName, secondName, lastName)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(stringResource(R.string.admin_home_title))
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.admin_all_retakes_title),
                    style = MaterialTheme.typography.headlineSmall
                )
                Button(onClick = onAddRetake) {
                    Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add))
                    Text(stringResource(R.string.add), modifier = Modifier.padding(start = 4.dp))
                }
            }
            when {
                uiState.allRetakesLoading -> {
                    Text(stringResource(R.string.loading_retakes), style = MaterialTheme.typography.bodyMedium)
                }
                uiState.allRetakesError != null -> {
                    Text(text = uiState.allRetakesError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
                }
                uiState.allRetakes.isEmpty() -> {
                    Text(stringResource(R.string.no_retakes), style = MaterialTheme.typography.bodyMedium)
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(bottom = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.allRetakes) { retake ->
                            val subjectTitle = uiState.subjects.find { it.id == retake.subjectId }?.title
                            Column {
                                Card(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 8.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(text = stringResource(R.string.admin_retake_subject_type, subjectTitle ?: "", retake.type.take(3)), style = MaterialTheme.typography.titleMedium)
                                                Text(text = stringResource(R.string.retake_prefix_place, retake.place), style = MaterialTheme.typography.bodySmall)
                                                Text(text = stringResource(R.string.retake_prefix_start, formatInstantToHuman(retake.startAt)), style = MaterialTheme.typography.bodySmall)
                                                Text(text = stringResource(R.string.retake_prefix_end, formatInstantToHuman(retake.endAt)), style = MaterialTheme.typography.bodySmall)
                                            }
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                                            ) {
                                                if (retake.startAt > Instant.now()){
                                                    IconButton(
                                                        onClick = { onEditRetake(retake.id) },
                                                        modifier = Modifier.padding(0.dp),
                                                    ) {
                                                        Icon(
                                                            Icons.Default.Edit,
                                                            contentDescription = stringResource(R.string.edit),
                                                            tint = MaterialTheme.colorScheme.primary
                                                        )
                                                    }
                                                }
                                                IconButton(
                                                    onClick = { onDeleteRetake(retake.id) },
                                                    modifier = Modifier.padding(0.dp)
                                                ) {
                                                    Icon(
                                                        Icons.Default.Delete,
                                                            contentDescription = stringResource(R.string.delete),
                                                        tint = MaterialTheme.colorScheme.error
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                                RetakeCommentsCard(
                                    retakeId = retake.id,
                                    comments = uiState.allComments,
                                    modifier = Modifier.padding(horizontal = 0.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}