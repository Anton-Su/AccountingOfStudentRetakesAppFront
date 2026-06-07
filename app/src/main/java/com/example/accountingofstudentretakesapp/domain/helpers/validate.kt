package com.example.accountingofstudentretakesapp.domain.helpers

fun validate(placeValue: String, teacherValue: String, overallValue: String, commentText: String): String? {
    val place = placeValue.toIntOrNull()
    val teacher = teacherValue.toIntOrNull()
    val overall = overallValue.toIntOrNull()
    val comment = commentText.trim()
    return when {
        place == null -> "Оцените аудиторию"
        place !in 0..10 -> "Оценка за аудиторию должна быть от 0 до 10"
        teacher == null -> "Оцените преподавателя"
        teacher !in 0..10 -> "Оценка преподавателя должна быть от 0 до 10"
        overall == null -> "Введите общую оценку"
        overall !in 0..100 -> "Общая оценка должна быть от 0 до 100"
        comment.length > 500 -> "Комментарий не должен быть длиннее 500 символов"
        else -> null
    }
}