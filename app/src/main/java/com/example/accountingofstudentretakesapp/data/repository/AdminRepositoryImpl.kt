package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toCommentDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toRetakeDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toTeacherDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDto.toRedactRetakeDto
import com.example.accountingofstudentretakesapp.domain.mapper.toDto.toСreateRetakeRequestDto
import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateRetakeRequest
import com.example.accountingofstudentretakesapp.domain.model.requests.RedactRetakeRequest
import com.example.accountingofstudentretakesapp.domain.repository.AdminRepository

/**
 * Реализация репозитория для административных операций.
 * Взаимодействует с сервером через [KtorClient].
 */
class AdminRepositoryImpl : AdminRepository {

    /**
     * Возвращает список преподавателей по дисциплине.
     *
     * @param discipline название дисциплины
     * @return список преподавателей
     */
    override suspend fun getTeachersByDiscipline(discipline: String): List<Teacher> {
        return KtorClient.getTeachersByDiscipline(discipline).map { it.toTeacherDomain() }
    }

    /**
     * Создаёт новую пересдачу.
     *
     * @param request данные для создания пересдачи
     * @return созданная пересдача
     */
    override suspend fun createRetake(request: CreateRetakeRequest): Retake {
        return KtorClient.createRetake(request.toСreateRetakeRequestDto()).toRetakeDomain()
    }

    /**
     * Обновляет существующую пересдачу.
     *
     * @param request данные для обновления пересдачи включая идентификатор
     * @return обновлённая пересдача
     */
    override suspend fun updateRetake(request: RedactRetakeRequest): Retake {
        return KtorClient.updateRetake(request.id, request.toRedactRetakeDto()).toRetakeDomain()
    }

    /**
     * Удаляет пересдачу по идентификатору.
     *
     * @param id идентификатор пересдачи
     */
    override suspend fun deleteRetake(id: Long) {
        KtorClient.deleteRetake(id)
    }

    /**
     * Возвращает список всех комментариев.
     *
     * @return список комментариев
     */
    override suspend fun getAllComments(): List<Comment> {
        return KtorClient.getAllComments().map { it.toCommentDomain() }
    }

    /**
     * Возвращает список всех пересдач.
     *
     * @return список пересдач
     */
    override suspend fun getAllRetakes(): List<Retake> {
        return KtorClient.getAllRetakes().map { it.toRetakeDomain() }
    }
}

