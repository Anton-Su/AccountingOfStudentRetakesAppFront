package com.example.accountingofstudentretakesapp.domain.helpers

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import com.example.accountingofstudentretakesapp.R

/**
 * Валидирует поля формы комментария к пересдаче.
 *
 * @param placeValue оценка за аудиторию в виде строки (ожидается число от 0 до 10)
 * @param teacherValue оценка за преподавателя в виде строки (ожидается число от 0 до 10)
 * @param overallValue общая оценка в виде строки (ожидается число от 0 до 100)
 * @param commentText текст комментария (не более 500 символов)
 * @return код ошибки или null если все поля валидны
 */

fun validate(placeValue: String, teacherValue: String, overallValue: String, commentText: String): ValidationError? {
    val place = placeValue.toIntOrNull()
    val teacher = teacherValue.toIntOrNull()
    val overall = overallValue.toIntOrNull()
    val comment = commentText.trim()
    return when {
        place == null -> ValidationError.PLACE_REQUIRED
        place !in 0..10 -> ValidationError.PLACE_RANGE
        teacher == null -> ValidationError.TEACHER_REQUIRED
        teacher !in 0..10 -> ValidationError.TEACHER_RANGE
        overall == null -> ValidationError.OVERALL_REQUIRED
        overall !in 0..100 -> ValidationError.OVERALL_RANGE
        comment.length > 500 -> ValidationError.COMMENT_LENGTH
        else -> null
    }
}