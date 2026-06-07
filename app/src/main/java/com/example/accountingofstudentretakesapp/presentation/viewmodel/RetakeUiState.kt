package com.example.accountingofstudentretakesapp.presentation.viewmodel

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.RetakeDetails
import com.example.accountingofstudentretakesapp.domain.model.StudentDebt
import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRank
import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.model.Teacher

/**
 * Состояние UI для всего приложения.
 * Содержит данные и состояния загрузки для всех экранов.
 *
 * Объединяет состояния всех ролей (студент, преподаватель, администратор)
 * в одном классе, так как используется единый [RetakeViewModel].
 */
data class RetakeUiState(
    /** Флаг загрузки при авторизации */
    val isLoading: Boolean = false,
    /** Сообщение об ошибке авторизации */
    val errorMessage: String? = null,
    /** Список пересдач преподавателя */
    val teacherRetakes: List<Retake> = emptyList(),
    /** Флаг загрузки пересдач преподавателя */
    val teacherRetakesLoading: Boolean = false,
    /** Ошибка загрузки пересдач преподавателя */
    val teacherRetakesError: String? = null,
    /** Детальная информация о пересдаче включая список студентов */
    val teacherRetakeDetails: RetakeDetails? = null,
    /** Флаг загрузки деталей пересдачи */
    val teacherRetakeDetailsLoading: Boolean = false,
    /** Ошибка загрузки деталей пересдачи */
    val teacherRetakeDetailsError: String? = null,
    /** Список всех пересдач */
    val allRetakes: List<Retake> = emptyList(),
    /** Флаг загрузки всех пересдач */
    val allRetakesLoading: Boolean = false,
    /** Ошибка загрузки всех пересдач */
    val allRetakesError: String? = null,
    /** Список всех предметов */
    val subjects: List<Subject> = emptyList(),
    /** Флаг загрузки предметов */
    val subjectsLoading: Boolean = false,
    /** Ошибка загрузки предметов */
    val subjectsError: String? = null,
    /** Список преподавателей по выбранной дисциплине */
    val teachersByDiscipline: List<Teacher> = emptyList(),
    /** Флаг загрузки преподавателей по дисциплине */
    val teachersByDisciplineLoading: Boolean = false,
    /** Ошибка загрузки преподавателей по дисциплине */
    val teachersByDisciplineError: String? = null,
    /** Флаг загрузки при создании пересдачи */
    val createRetakeLoading: Boolean = false,
    /** Ошибка создания пересдачи */
    val createRetakeError: String? = null,
    /** Флаг загрузки при удалении пересдачи */
    val deleteRetakeLoading: Boolean = false,
    /** Ошибка удаления пересдачи */
    val deleteRetakeError: String? = null,
    /** Флаг загрузки при редактировании пересдачи */
    val redactRetakeLoading: Boolean = false,
    /** Ошибка редактирования пересдачи */
    val redactRetakeError: String? = null,
    /** Список всех комментариев */
    val allComments: List<Comment> = emptyList(),
    /** Флаг загрузки комментариев */
    val allCommentsLoading: Boolean = false,
    /** Ошибка загрузки комментариев */
    val allCommentsError: String? = null,
    /** Список долгов студента */
    val studentDebts: List<StudentDebt> = emptyList(),
    /** Флаг загрузки долгов студента */
    val studentDebtsLoading: Boolean = false,
    /** Ошибка загрузки долгов студента */
    val studentDebtsError: String? = null,
    /** Рейтинг студента по количеству долгов */
    val studentDebtRank: StudentDebtRank? = null,
    /** Флаг загрузки рейтинга долгов */
    val studentDebtRankLoading: Boolean = false,
    /** Ошибка загрузки рейтинга долгов */
    val studentDebtRankError: String? = null,
    /** Список доступных пересдач для студента */
    val availableRetakes: List<Retake> = emptyList(),
    /** Флаг загрузки доступных пересдач */
    val availableRetakesLoading: Boolean = false,
    /** Ошибка загрузки доступных пересдач */
    val availableRetakesError: String? = null,
    /** Список пересдач на которые записан студент */
    val enrolledRetakes: List<Retake> = emptyList(),
    /** Флаг загрузки записанных пересдач */
    val enrolledRetakesLoading: Boolean = false,
    /** Ошибка загрузки записанных пересдач */
    val enrolledRetakesError: String? = null,
    /** Флаг загрузки при создании комментария */
    val createCommentLoading: Boolean = false,
    /** Ошибка создания комментария */
    val createCommentError: String? = null,
    /** Флаг загрузки при записи на пересдачу */
    val enrollRetakeLoading: Boolean = false,
    /** Ошибка записи на пересдачу */
    val enrollRetakeError: String? = null,
    /** Флаг загрузки при отмене записи на пересдачу */
    val cancelRetakeLoading: Boolean = false,
    /** Ошибка отмены записи на пересдачу */
    val cancelRetakeError: String? = null,
)