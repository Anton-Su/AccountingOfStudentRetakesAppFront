package com.example.accountingofstudentretakesapp.presentation.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.presentation.ui.component.RetakeFormScreen
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState

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
        title = "Создать пересдачу",
        submitButtonText = "Создать",
        isLoading = uiState.createRetakeLoading,
        uiState = uiState,
        onClearTeachers = onClearTeachers,
        onLoadSubjects = onLoadSubjects,
        onLoadTeachers = onLoadTeachers,
        onSubmit = onCreateRetake,
        onBack = onBack
    )
}






