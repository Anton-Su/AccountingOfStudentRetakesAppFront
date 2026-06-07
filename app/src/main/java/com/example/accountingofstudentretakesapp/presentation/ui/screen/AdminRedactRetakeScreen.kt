package com.example.accountingofstudentretakesapp.presentation.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.accountingofstudentretakesapp.R
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.requests.RedactRetakeRequest
import com.example.accountingofstudentretakesapp.presentation.ui.component.RetakeFormScreen
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState
import java.time.Instant

/**
 * Экран редактирования существующей пересдачи для администратора.
 *
 * Обёртка над [RetakeFormScreen] с предзаполненными значениями
 * из текущей пересдачи найденной по [retakeId] в [uiState.allRetakes].
 *
 * Использует ту же форму что и [AdminCreateRetakeScreen] — чтобы
 * не дублировать UI. Маппит [CreateRetakeRequest] в [RedactRetakeRequest]
 * добавляя [retakeId].
 *
 * @param retakeId ID редактируемой пересдачи
 * @param uiState общий UI стейт — берёт пересдачу из [RetakeUiState.allRetakes]
 * @param onLoadSubjects загрузить список предметов
 * @param onLoadTeachers загрузить преподавателей по названию предмета
 * @param onClearTeachers очистить список преподавателей
 * @param onRedactRetake отправить запрос на редактирование пересдачи
 * @param onBack вернуться назад
 */
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
        title = stringResource(R.string.edit_retake_title),
        submitButtonText = stringResource(R.string.edit_retake_submit),
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