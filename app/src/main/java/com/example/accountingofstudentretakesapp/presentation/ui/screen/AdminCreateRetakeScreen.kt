package com.example.accountingofstudentretakesapp.presentation.ui.screen

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.accountingofstudentretakesapp.presentation.ui.component.DateTimePickerDialogs
import com.example.accountingofstudentretakesapp.presentation.ui.component.DateTimePickerField
import com.example.accountingofstudentretakesapp.presentation.ui.component.RetakeFormScreen
import com.example.accountingofstudentretakesapp.presentation.ui.component.RetakeTypeSelector
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminCreateRetakeScreen(uiState: RetakeUiState,
                            onLoadSubjects: () -> Unit,
                            onLoadTeachers: (String) -> Unit,
                            onClearTeachers: () -> Unit,
                            onCreateRetake: (startAt: Instant, endAt: Instant, teacherIds: List<Long>, subjectId: Long, type: String, place: String, admission: String?) -> Unit,
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






