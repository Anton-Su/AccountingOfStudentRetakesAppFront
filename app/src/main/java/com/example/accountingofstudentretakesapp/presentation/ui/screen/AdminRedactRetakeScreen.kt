package com.example.accountingofstudentretakesapp.presentation.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.accountingofstudentretakesapp.presentation.ui.component.RetakeFormScreen
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminRedactRetakeScreen(retakeId: Long, uiState: RetakeUiState,
                            onLoadSubjects: () -> Unit,
                            onLoadTeachers: (String) -> Unit,
                            onClearTeachers: () -> Unit,
                            onRedactRetake: (id: Long, startAt: Instant, endAt: Instant, teacherIds: List<Long>, subjectId: Long, type: String, place: String, admission: String?) -> Unit,
                            onBack: () -> Unit
) {
    val retake = uiState.allRetakes.find { it.id == retakeId }
    RetakeFormScreen(
        title = "Редактировать пересдачу",
        submitButtonText = "Сохранить",
        isLoading = uiState.redactRetakeLoading,
        initialType = retake?.type,
        initialPlace = retake?.place ?: "",
        initialStartAt = retake!!.startAt,
        initialEndAt = retake.endAt,
        initialAdmission = retake.admission ?: "",
        initialSubjectId = retake.subjectId,
        initialTeacherIds = retake.teacherIds,
        uiState = uiState,
        onClearTeachers = onClearTeachers,
        onLoadSubjects = onLoadSubjects,
        onLoadTeachers = onLoadTeachers,
        onSubmit = { startAt, endAt, teacherIds, subjectId, type, place, admission ->
            onRedactRetake(retakeId, startAt, endAt, teacherIds, subjectId, type, place, admission)
        },
        onBack = onBack
    )
}



