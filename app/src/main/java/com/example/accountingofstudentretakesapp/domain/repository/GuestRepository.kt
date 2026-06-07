package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.Subject

/** Репозиторий для неавторизованных пользователей. */
interface GuestRepository {
    /** Возвращает список всех доступных предметов. */
    suspend fun getSubjects(): List<Subject>
}