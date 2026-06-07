package com.example.accountingofstudentretakesapp.domain.model

/**
 * Роль пользователя в системе для показа демонстрации функций.
 *
 * [STUDENT] — студент
 * [TEACHER] — преподаватель
 * [ADMIN] — администратор
 * [NONE] — изначальная ситуация
 */
enum class UserRole {
    STUDENT,
    TEACHER,
    ADMIN,
    NONE
}

