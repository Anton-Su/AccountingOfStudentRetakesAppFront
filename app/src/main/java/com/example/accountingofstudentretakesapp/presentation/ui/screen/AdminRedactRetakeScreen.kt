package com.example.accountingofstudentretakesapp.presentation.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.requests.RedactRetakeRequest
import com.example.accountingofstudentretakesapp.presentation.ui.component.RetakeFormScreen
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminRedactRetakeScreen(retakeId: Long, uiState: RetakeUiState,
                            onLoadSubjects: () -> Unit,
                            onLoadTeachers: (String) -> Unit,
                            onClearTeachers: () -> Unit,
                            onRedactRetake: (RedactRetakeRequest) -> Unit,
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
        // да, мне нравится одна форма, и из-за этого такая странная конструкция с редактированием, но я не хочу делать две почти одинаковые формы
        // маппер ну такое здесь
        onSubmit = { request ->
            onRedactRetake(
                RedactRetakeRequest(
                    id = retakeId,
                    startAt = request.startAt,
                    endAt = request.endAt,
                    teacherIds = request.teacherIds,
                    subjectId = request.subjectId,
                    type = request.type,
                    place = request.place,
                    admission = request.admission
                )
            )
        },
        onBack = onBack
    )
}



