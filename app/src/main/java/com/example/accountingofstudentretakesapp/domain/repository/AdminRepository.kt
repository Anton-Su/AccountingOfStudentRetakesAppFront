package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.requests.RedactRetakeRequest

/** Репозиторий для административных операций. */
interface AdminRepository {
    /**
     * Возвращает список преподавателей по дисциплине.
     * @param discipline название дисциплины
     */
    suspend fun getTeachersByDiscipline(discipline: String): List<Teacher>

    /**
     * Создаёт новую пересдачу.
     * @param request данные для создания пересдачи
     * @return созданная пересдача
     */
    suspend fun createRetake(request: CreateRetakeRequest): Retake

    /**
     * Обновляет существующую пересдачу.
     * @param request данные для обновления включая идентификатор
     * @return обновлённая пересдача
     */
    suspend fun updateRetake(request: RedactRetakeRequest): Retake

    /**
     * Удаляет пересдачу по идентификатору.
     * @param id идентификатор пересдачи
     */
    suspend fun deleteRetake(id: Long)

    /** Возвращает список всех комментариев. */
    suspend fun getAllComments(): List<Comment>

    /** Возвращает список всех пересдач. */
    suspend fun getAllRetakes(): List<Retake>
}