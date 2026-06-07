package com.example.accountingofstudentretakesapp.presentation.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.accountingofstudentretakesapp.R
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.presentation.ui.component.RetakeFormScreen
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState

/**
 * Экран создания новой пересдачи для администратора.
 *
 * Обёртка над [RetakeFormScreen] с предустановленными параметрами
 * для создания — пустые начальные значения, заголовок "Создать пересдачу".
 *
 * @param uiState общий UI стейт
 * @param onLoadSubjects загрузить список предметов
 * @param onLoadTeachers загрузить преподавателей по названию предмета
 * @param onClearTeachers очистить список преподавателей
 * @param onCreateRetake отправить запрос на создание пересдачи
 * @param onBack вернуться назад
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminCreateRetakeScreen(uiState: RetakeUiState,
                            onLoadSubjects: () -> Unit,
                            onLoadTeachers: (String) -> Unit,
                            onClearTeachers: () -> Unit,
                            onCreateRetake: (CreateRetakeRequest) -> Unit,
                            onBack: () -> Unit)
{
    RetakeFormScreen(
        title = stringResource(R.string.create_retake_title),
        submitButtonText = stringResource(R.string.create_retake_submit),
        isLoading = uiState.createRetakeLoading,
        uiState = uiState,
        onClearTeachers = onClearTeachers,
        onLoadSubjects = onLoadSubjects,
        onLoadTeachers = onLoadTeachers,
        onSubmit = onCreateRetake,
        onBack = onBack
    )
}






