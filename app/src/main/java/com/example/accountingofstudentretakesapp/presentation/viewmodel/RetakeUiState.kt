package com.example.accountingofstudentretakesapp.presentation.viewmodel

import com.example.accountingofstudentretakesapp.presentation.model.Comment
import com.example.accountingofstudentretakesapp.presentation.model.Retake
import com.example.accountingofstudentretakesapp.presentation.model.RetakeDetails
import com.example.accountingofstudentretakesapp.presentation.model.StudentDebt
import com.example.accountingofstudentretakesapp.presentation.model.StudentDebtRank
import com.example.accountingofstudentretakesapp.presentation.model.Subject
import com.example.accountingofstudentretakesapp.presentation.model.Teacher

data class RetakeUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val teacherRetakes: List<Retake> = emptyList(),
    val teacherRetakesLoading: Boolean = false,
    val teacherRetakesError: String? = null,
    val teacherRetakeDetails: RetakeDetails? = null,
    val teacherRetakeDetailsLoading: Boolean = false,
    val teacherRetakeDetailsError: String? = null,
    val allRetakes: List<Retake> = emptyList(),
    val allRetakesLoading: Boolean = false,
    val allRetakesError: String? = null,
    val subjects: List<Subject> = emptyList(),
    val subjectsLoading: Boolean = false,
    val subjectsError: String? = null,
    val teachersByDiscipline: List<Teacher> = emptyList(),
    val teachersByDisciplineLoading: Boolean = false,
    val teachersByDisciplineError: String? = null,
    val createRetakeLoading: Boolean = false,
    val createRetakeError: String? = null,
    val deleteRetakeLoading: Boolean = false,
    val deleteRetakeError: String? = null,
    val redactRetakeLoading: Boolean = false,
    val redactRetakeError: String? = null,
    val allComments: List<Comment> = emptyList(),
    val allCommentsLoading: Boolean = false,
    val allCommentsError: String? = null,
    val studentDebts: List<StudentDebt> = emptyList(),
    val studentDebtsLoading: Boolean = false,
    val studentDebtsError: String? = null,
    val studentDebtRank: StudentDebtRank? = null,
    val studentDebtRankLoading: Boolean = false,
    val studentDebtRankError: String? = null,
    val availableRetakes: List<Retake> = emptyList(),
    val availableRetakesLoading: Boolean = false,
    val availableRetakesError: String? = null,
    val enrolledRetakes: List<Retake> = emptyList(),
    val enrolledRetakesLoading: Boolean = false,
    val enrolledRetakesError: String? = null,
    val createCommentLoading: Boolean = false,
    val createCommentError: String? = null,
    val enrollRetakeLoading: Boolean = false,
    val enrollRetakeError: String? = null,
    val cancelRetakeLoading: Boolean = false,
    val cancelRetakeError: String? = null,
)