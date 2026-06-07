package com.example.accountingofstudentretakesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.accountingofstudentretakesapp.data.remote.SettingsDataStore
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateCommentRequest
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.requests.GradeRequest
import com.example.accountingofstudentretakesapp.domain.model.requests.LoginRequest
import com.example.accountingofstudentretakesapp.domain.model.requests.RedactRetakeRequest
import com.example.accountingofstudentretakesapp.domain.usecase.CancelRetakeEnrollmentUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.CreateCommentUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.CreateRetakeUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.DeleteRetakeUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.EnrollToRetakeUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetAllCommentsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetAllRetakesUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetAvailableRetakesUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetCachedCommentsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetCachedSubjectsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetCachedTeachersByDisciplineUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetCurrentUserUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetEnrolledRetakesUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetRetakeDetailsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetStudentDebtRankUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetStudentDebtsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetSubjectsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetTeacherRetakesUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetTeachersByDisciplineUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GradeStudentUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.LoginUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.LogoutUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.RedactRetakeUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.SaveCommentsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.SaveSubjectsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.SaveTeachersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Общий ViewModel для всего приложения.
 * Содержит логику для всех ролей: студент, преподаватель, администратор.
 */
class RetakeViewModel( // да-да, GOD-like класс... (Hilt плачет)
    private val logoutUseCase: LogoutUseCase,
    private val settingsDataStore: SettingsDataStore,
    private val saveCommentsUseCase: SaveCommentsUseCase,
    private val getCachedCommentsUseCase: GetCachedCommentsUseCase,
    private val saveSubjectsUseCase: SaveSubjectsUseCase,
    private val getCachedSubjectsUseCase: GetCachedSubjectsUseCase,
    private val saveTeachersUseCase: SaveTeachersUseCase,
    private val getCachedTeachersByDisciplineUseCase: GetCachedTeachersByDisciplineUseCase,
    private val loginUseCase: LoginUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getTeacherRetakesUseCase: GetTeacherRetakesUseCase,
    private val getRetakeDetailsUseCase: GetRetakeDetailsUseCase,
    private val gradeStudentUseCase: GradeStudentUseCase,
    private val getAllRetakesUseCase: GetAllRetakesUseCase,
    private val getSubjectsUseCase: GetSubjectsUseCase,
    private val getTeachersByDisciplineUseCase: GetTeachersByDisciplineUseCase,
    private val getAllCommentsUseCase: GetAllCommentsUseCase,
    private val createRetakeUseCase: CreateRetakeUseCase,
    private val deleteRetakeUseCase: DeleteRetakeUseCase,
    private val redactRetakeUseCase: RedactRetakeUseCase,
    private val getStudentDebtsUseCase: GetStudentDebtsUseCase,
    private val getStudentDebtRankUseCase: GetStudentDebtRankUseCase,
    private val getAvailableRetakesUseCase: GetAvailableRetakesUseCase,
    private val getEnrolledRetakesUseCase: GetEnrolledRetakesUseCase,
    private val enrollToRetakeUseCase: EnrollToRetakeUseCase,
    private val cancelRetakeEnrollmentUseCase: CancelRetakeEnrollmentUseCase,
    private val createCommentUseCase: CreateCommentUseCase) : ViewModel()
{
    private val _uiState = MutableStateFlow(RetakeUiState())
    val uiState: StateFlow<RetakeUiState> = _uiState.asStateFlow()

    init {
        loadSubjects()
    }
    /**
     * Авторизует пользователя и сохраняет профиль в DataStore.
     *
     * @param email электронная почта
     * @param password пароль
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            runCatching { loginUseCase(LoginRequest(email, password)) }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = error.message ?: "Ошибка входа")
                    }
                }
                .onSuccess {
                    loadSubjects()
                    val currentUser = getCurrentUserUseCase()
                    settingsDataStore.saveUserProfile(currentUser)
                    _uiState.update { it.copy(isLoading = false, errorMessage = null) }
                }
        }
    }

    /**
     * Выходит из системы и сбрасывает состояние UI.
     */
    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _uiState.update { RetakeUiState() }
        }
    }
    /**
     * Загружает список пересдач преподавателя.
     */
    fun loadTeacherRetakes() {
        viewModelScope.launch {
            _uiState.update { it.copy(teacherRetakesLoading = true, teacherRetakesError = null) }
            runCatching { getTeacherRetakesUseCase() }
                .onSuccess { retakes ->
                    _uiState.update { it.copy(teacherRetakes = retakes, teacherRetakesLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            teacherRetakesLoading = false,
                            teacherRetakesError = error.message ?: "Не удалось загрузить пересдачи"
                        )
                    }
                }
        }
    }

    /**
     * Загружает детальную информацию о пересдаче для преподавателя.
     *
     * @param retakeId идентификатор пересдачи
     */
    fun loadTeacherRetakeDetails(retakeId: Long) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    teacherRetakeDetailsLoading = true,
                    teacherRetakeDetailsError = null,
                    teacherRetakeDetails = null
                )
            }
            runCatching { getRetakeDetailsUseCase(retakeId) }
                .onSuccess { details ->
                    _uiState.update { it.copy(teacherRetakeDetails = details, teacherRetakeDetailsLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            teacherRetakeDetailsLoading = false,
                            teacherRetakeDetailsError = error.message ?: "Не удалось загрузить детали пересдачи"
                        )
                    }
                }
        }
    }

    /**
     * Выставляет оценку студенту на пересдаче.
     * После успешного выставления оценки студент удаляется из списка.
     *
     * @param retakeId идентификатор пересдачи
     * @param studentId идентификатор студента
     * @param score оценка студента
     */
    fun gradeStudent(retakeId: Long, studentId: Long, score: Int) {
        viewModelScope.launch {
            runCatching { gradeStudentUseCase(retakeId, studentId, GradeRequest(score)) }
                .onSuccess {
                    _uiState.update { currentState ->
                        val updatedDetails = currentState.teacherRetakeDetails?.copy(
                            enrollments = currentState.teacherRetakeDetails.enrollments.filter {
                                it.studentId != studentId
                            }
                        )
                        currentState.copy(teacherRetakeDetails = updatedDetails)
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(teacherRetakeDetailsError = error.message ?: "Не удалось выставить оценку")
                    }
                }
        }
    }
    /**
     * Загружает список всех пересдач.
     */
    fun loadAllRetakes() {
        viewModelScope.launch {
            _uiState.update { it.copy(allRetakesLoading = true, allRetakesError = null) }
            runCatching { getAllRetakesUseCase() }
                .onSuccess { retakes ->
                    _uiState.update { it.copy(allRetakes = retakes, allRetakesLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            allRetakesLoading = false,
                            allRetakesError = error.message ?: "Не удалось загрузить пересдачи"
                        )
                    }
                }
        }
    }

    /**
     * Загружает список предметов.
     * При ошибке сети пытается загрузить из локального кэша.
     */
    fun loadSubjects() {
        viewModelScope.launch {
            _uiState.update { it.copy(subjectsLoading = true, subjectsError = null) }
            runCatching { getSubjectsUseCase() }
                .onSuccess { subjects ->
                    _uiState.update { it.copy(subjects = subjects, subjectsLoading = false) }
                    runCatching { saveSubjectsUseCase(subjects) }.onFailure { }
                }
                .onFailure { error ->
                    runCatching { getCachedSubjectsUseCase() }
                        .onSuccess { cached ->
                            if (cached.isNotEmpty()) {
                                _uiState.update { it.copy(subjects = cached, subjectsLoading = false, subjectsError = null) }
                            } else {
                                _uiState.update {
                                    it.copy(subjectsLoading = false, subjectsError = error.message ?: "Не удалось загрузить предметы")
                                }
                            }
                        }
                        .onFailure {
                            _uiState.update {
                                it.copy(subjectsLoading = false, subjectsError = error.message ?: "Не удалось загрузить предметы")
                            }
                        }
                }
        }
    }

    /**
     * Загружает преподавателей по дисциплине.
     * При ошибке сети пытается загрузить из локального кэша.
     *
     * @param discipline название дисциплины
     */
    fun loadTeachersByDiscipline(discipline: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(teachersByDisciplineLoading = true, teachersByDisciplineError = null) }
            runCatching { getTeachersByDisciplineUseCase(discipline) }
                .onSuccess { teachers ->
                    _uiState.update { it.copy(teachersByDiscipline = teachers, teachersByDisciplineLoading = false) }
                    runCatching { saveTeachersUseCase(teachers) }.onFailure { }
                }
                .onFailure { error ->
                    runCatching { getCachedTeachersByDisciplineUseCase(discipline) }
                        .onSuccess { cached ->
                            if (cached.isNotEmpty()) {
                                _uiState.update { it.copy(teachersByDiscipline = cached, teachersByDisciplineLoading = false, teachersByDisciplineError = null) }
                            } else {
                                _uiState.update {
                                    it.copy(teachersByDisciplineLoading = false, teachersByDisciplineError = error.message ?: "Не удалось загрузить преподавателей")
                                }
                            }
                        }
                        .onFailure {
                            _uiState.update {
                                it.copy(teachersByDisciplineLoading = false, teachersByDisciplineError = error.message ?: "Не удалось загрузить преподавателей")
                            }
                        }
                }
        }
    }

    /**
     * Загружает все комментарии.
     * При ошибке сети пытается загрузить из локального кэша.
     */
    fun loadAllComments() {
        viewModelScope.launch {
            _uiState.update { it.copy(allCommentsLoading = true, allCommentsError = null) }
            runCatching { getAllCommentsUseCase() }
                .onSuccess { comments ->
                    _uiState.update { it.copy(allComments = comments, allCommentsLoading = false) }
                    runCatching { saveCommentsUseCase(comments) }.onFailure { }
                }
                .onFailure { error ->
                    runCatching { getCachedCommentsUseCase() }
                        .onSuccess { cached ->
                            if (cached.isNotEmpty()) {
                                _uiState.update { it.copy(allComments = cached, allCommentsLoading = false) }
                            } else {
                                _uiState.update {
                                    it.copy(allCommentsLoading = false, allCommentsError = error.message ?: "Не удалось загрузить комментарии")
                                }
                            }
                        }
                        .onFailure {
                            _uiState.update {
                                it.copy(allCommentsLoading = false, allCommentsError = error.message ?: "Не удалось загрузить комментарии")
                            }
                        }
                }
        }
    }

    /**
     * Создаёт новую пересдачу и обновляет список пересдач.
     *
     * @param request данные для создания пересдачи
     * @param onSuccess вызывается при успешном создании
     * @param onError вызывается при ошибке с сообщением об ошибке
     */
    fun createRetake(request: CreateRetakeRequest, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(createRetakeLoading = true, createRetakeError = null) }
            runCatching { createRetakeUseCase(request) }
                .onSuccess {
                    _uiState.update { it.copy(createRetakeLoading = false) }
                    loadAllRetakes()
                    onSuccess()
                }
                .onFailure { error ->
                    val errorMsg = error.message ?: "Не удалось создать пересдачу"
                    _uiState.update { it.copy(createRetakeLoading = false, createRetakeError = errorMsg) }
                    onError(errorMsg)
                }
        }
    }

    /**
     * Удаляет пересдачу и обновляет список пересдач.
     *
     * @param retakeId идентификатор пересдачи
     * @param onSuccess вызывается при успешном удалении
     * @param onError вызывается при ошибке с сообщением об ошибке
     */
    fun deleteRetake(retakeId: Long, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(deleteRetakeLoading = true, deleteRetakeError = null) }
            runCatching { deleteRetakeUseCase(retakeId) }
                .onSuccess {
                    _uiState.update { it.copy(deleteRetakeLoading = false) }
                    loadAllRetakes()
                    onSuccess()
                }
                .onFailure { error ->
                    val errorMsg = error.message ?: "Не удалось удалить пересдачу"
                    _uiState.update { it.copy(deleteRetakeLoading = false, deleteRetakeError = errorMsg) }
                    onError(errorMsg)
                }
        }
    }

    /**
     * Редактирует пересдачу и обновляет список пересдач.
     *
     * @param request данные для редактирования включая идентификатор
     * @param onSuccess вызывается при успешном редактировании
     * @param onError вызывается при ошибке с сообщением об ошибке
     */
    fun redactRetake(request: RedactRetakeRequest, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(redactRetakeLoading = true, redactRetakeError = null) }
            runCatching { redactRetakeUseCase(request = request) }
                .onSuccess {
                    _uiState.update { it.copy(redactRetakeLoading = false) }
                    loadAllRetakes()
                    onSuccess()
                }
                .onFailure { error ->
                    val errorMsg = error.message ?: "Не удалось редактировать пересдачу"
                    _uiState.update { it.copy(redactRetakeLoading = false, redactRetakeError = errorMsg) }
                    onError(errorMsg)
                }
        }
    }

    /**
     * Очищает список преподавателей по дисциплине.
     * Вызывается при уходе с экрана создания/редактирования пересдачи.
     */
    fun clearTeachersByDiscipline() {
        _uiState.update { it.copy(teachersByDiscipline = emptyList()) }
    }

    /**
     * Загружает список долгов студента.
     */
    fun loadStudentDebts() {
        viewModelScope.launch {
            val studentId = settingsDataStore.userIdFlow.first()
            _uiState.update { it.copy(studentDebtsLoading = true, studentDebtsError = null) }
            runCatching { getStudentDebtsUseCase(studentId) }
                .onSuccess { debts ->
                    _uiState.update { it.copy(studentDebts = debts, studentDebtsLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(studentDebtsLoading = false, studentDebtsError = error.message ?: "Не удалось загрузить долги")
                    }
                }
        }
    }

    /**
     * Загружает рейтинг студента по количеству долгов.
     */
    fun loadStudentDebtRank() {
        viewModelScope.launch {
            val studentId = settingsDataStore.userIdFlow.first()
            _uiState.update { it.copy(studentDebtRankLoading = true, studentDebtRankError = null) }
            runCatching { getStudentDebtRankUseCase(studentId) }
                .onSuccess { rank ->
                    _uiState.update { it.copy(studentDebtRank = rank, studentDebtRankLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(studentDebtRankLoading = false, studentDebtRankError = error.message ?: "Не удалось загрузить рейтинг долгов")
                    }
                }
        }
    }

    /**
     * Загружает список доступных пересдач для студента.
     */
    fun loadAvailableRetakes() {
        viewModelScope.launch {
            val studentId = settingsDataStore.userIdFlow.first()
            _uiState.update { it.copy(availableRetakesLoading = true, availableRetakesError = null) }
            runCatching { getAvailableRetakesUseCase(studentId) }
                .onSuccess { retakes ->
                    _uiState.update { it.copy(availableRetakes = retakes, availableRetakesLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(availableRetakesLoading = false, availableRetakesError = error.message ?: "Не удалось загрузить доступные пересдачи")
                    }
                }
        }
    }

    /**
     * Загружает список пересдач на которые записан студент.
     */
    fun loadEnrolledRetakes() {
        viewModelScope.launch {
            val studentId = settingsDataStore.userIdFlow.first()
            _uiState.update { it.copy(enrolledRetakesLoading = true, enrolledRetakesError = null) }
            runCatching { getEnrolledRetakesUseCase(studentId) }
                .onSuccess { retakes ->
                    _uiState.update { it.copy(enrolledRetakes = retakes, enrolledRetakesLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(enrolledRetakesLoading = false, enrolledRetakesError = error.message ?: "Не удалось загрузить записанные пересдачи")
                    }
                }
        }
    }

    /**
     * Записывает студента на пересдачу.
     * После успешной записи перемещает пересдачу из доступных в записанные.
     *
     * @param debtId идентификатор долга
     * @param retakeId идентификатор пересдачи
     * @param onSuccess вызывается при успешной записи
     * @param onError вызывается при ошибке с сообщением об ошибке
     */
    fun enrollToRetake(debtId: Long, retakeId: Long, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val studentId = settingsDataStore.userIdFlow.first()
            _uiState.update { it.copy(enrollRetakeLoading = true, enrollRetakeError = null) }
            runCatching { enrollToRetakeUseCase(studentId, debtId, retakeId) }
                .onSuccess {
                    _uiState.update { currentState ->
                        val retake = currentState.availableRetakes.find { it.id == retakeId }
                        currentState.copy(
                            availableRetakes = currentState.availableRetakes.filter { it.id != retakeId },
                            enrolledRetakes = if (retake != null) currentState.enrolledRetakes + retake else currentState.enrolledRetakes,
                            enrollRetakeLoading = false
                        )
                    }
                    onSuccess()
                }
                .onFailure { error ->
                    val message = error.message ?: "Не удалось записаться на пересдачу"
                    _uiState.update { it.copy(enrollRetakeLoading = false, enrollRetakeError = message) }
                    onError(message)
                }
        }
    }

    /**
     * Отменяет запись студента на пересдачу.
     * После успешной отмены перемещает пересдачу из записанных в доступные.
     *
     * @param debtId идентификатор долга
     * @param retakeId идентификатор пересдачи
     * @param onSuccess вызывается при успешной отмене
     * @param onError вызывается при ошибке с сообщением об ошибке
     */
    fun cancelRetakeEnrollment(debtId: Long, retakeId: Long, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val studentId = settingsDataStore.userIdFlow.first()
            _uiState.update { it.copy(cancelRetakeLoading = true, cancelRetakeError = null) }
            runCatching { cancelRetakeEnrollmentUseCase(studentId, debtId, retakeId) }
                .onSuccess {
                    _uiState.update { currentState ->
                        val retake = currentState.enrolledRetakes.find { it.id == retakeId }
                        currentState.copy(
                            enrolledRetakes = currentState.enrolledRetakes.filter { it.id != retakeId },
                            availableRetakes = if (retake != null) currentState.availableRetakes + retake else currentState.availableRetakes,
                            cancelRetakeLoading = false
                        )
                    }
                    onSuccess()
                }
                .onFailure { error ->
                    val message = error.message ?: "Не удалось отписаться от пересдачи"
                    _uiState.update { it.copy(cancelRetakeLoading = false, cancelRetakeError = message) }
                    onError(message)
                }
        }
    }

    /**
     * Создаёт комментарий к пересдаче от имени студента.
     *
     * @param gradePlace оценка за место проведения (0-10)
     * @param gradeTeacher оценка за преподавателя (0-10)
     * @param gradeOverall общая оценка (0-100)
     * @param comment текст комментария или null
     * @param retakeId идентификатор пересдачи
     * @param onSuccess вызывается при успешном создании
     * @param onError вызывается при ошибке с сообщением об ошибке
     */
    fun createComment(
        gradePlace: Int, gradeTeacher: Int, gradeOverall: Int,
        comment: String?, retakeId: Long,
        onSuccess: () -> Unit, onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val studentId = settingsDataStore.userIdFlow.first()
            _uiState.update { it.copy(createCommentLoading = true, createCommentError = null) }
            runCatching {
                createCommentUseCase(studentId, CreateCommentRequest(
                    gradePlace = gradePlace,
                    gradeTeacher = gradeTeacher,
                    gradeOverall = gradeOverall,
                    comment = comment,
                    retakeId = retakeId
                ))
            }
                .onSuccess {
                    _uiState.update { it.copy(createCommentLoading = false, createCommentError = null) }
                    onSuccess()
                }
                .onFailure { error ->
                    val message = error.message ?: "Не удалось отправить комментарий"
                    _uiState.update { it.copy(createCommentLoading = false, createCommentError = message) }
                    onError(message)
                }
        }
    }
}