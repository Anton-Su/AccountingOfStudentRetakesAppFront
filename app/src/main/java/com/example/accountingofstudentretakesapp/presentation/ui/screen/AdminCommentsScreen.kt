package com.example.accountingofstudentretakesapp.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState
import com.example.accountingofstudentretakesapp.presentation.ui.component.formatIsoDateTimeToHuman

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminCommentsScreen(
    uiState: RetakeUiState,
    onLoadComments: () -> Unit,
    onBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        onLoadComments()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Комментарии") },
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
                .padding(16.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            when {
                uiState.allCommentsLoading -> {
                    Text("Загрузка комментариев...", style = MaterialTheme.typography.bodyMedium)
                }
                uiState.allCommentsError != null -> {
                    Text(
                        text = uiState.allCommentsError,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                uiState.allComments.isEmpty() -> {
                    Text("Нет комментариев", style = MaterialTheme.typography.bodyMedium)
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(bottom = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.allComments) { comment ->
                            Card(modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(text = comment.studentFullName + "Оставил комментарий", style = MaterialTheme.typography.titleMedium)
                                    Text(text = comment.comment ?: "", style = MaterialTheme.typography.bodySmall)

                                    Row(modifier = Modifier.padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                        Icon(imageVector = Icons.Filled.Person, contentDescription = "Преподаватель")
                                        Text(text = "${comment.gradeteacher}/10", style = MaterialTheme.typography.bodyMedium)

                                    }
                                    Row(modifier = Modifier.padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Filled.Place, contentDescription = "Оценка по месту")
                                        Text(text = "Место: ${comment.gradeplace}/10", style = MaterialTheme.typography.bodyMedium)
                                    }
                                    Text(text = "Пара ${comment.subjectTitle} состоялась: ${formatIsoDateTimeToHuman(comment.retakeStartAt)}", style = MaterialTheme.typography.bodySmall)
                                    Text(text = "${formatIsoDateTimeToHuman(comment.retakeStartAt)} : ${formatIsoDateTimeToHuman(comment.retakeEndAt)}", style = MaterialTheme.typography.bodySmall)
                                    Text(text = "Итог: ${comment.gradeoverall}/100", style = MaterialTheme.typography.bodyMedium)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

