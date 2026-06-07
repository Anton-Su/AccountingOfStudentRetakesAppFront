package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toCommentDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toRetakeDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toStudentDebtDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toStudentDebtRankDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDto.toСreateCommentRequestDto
import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.StudentDebt
import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRank
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateCommentRequest
import com.example.accountingofstudentretakesapp.domain.repository.StudentRepository

/**
 * Реализация репозитория для операций студента.
 * Взаимодействует с сервером через [KtorClient].
 */
class StudentRepositoryImpl : StudentRepository {

    /**
     * Возвращает список долгов студента.
     *
     * @param studentId идентификатор студента
     * @return список долгов
     */
    override suspend fun findDebtsByStudentId(studentId: Long): List<StudentDebt> {
        return KtorClient.getStudentDebts(studentId).map { it.toStudentDebtDomain() }
    }

    /**
     * Возвращает список доступных пересдач для студента.
     *
     * @param studentId идентификатор студента
     * @return список доступных пересдач
     */
    override suspend fun findAvailableRetakes(studentId: Long): List<Retake> {
        return KtorClient.getAvailableRetakes(studentId).map { it.toRetakeDomain() }
    }

    /**
     * Возвращает список пересдач на которые записан студент.
     *
     * @param studentId идентификатор студента
     * @return список пересдач
     */
    override suspend fun findEnrolledRetakes(studentId: Long): List<Retake> {
        return KtorClient.getEnrolledRetakes(studentId).map { it.toRetakeDomain() }
    }

    /**
     * Записывает студента на пересдачу.
     *
     * @param studentId идентификатор студента
     * @param debtId идентификатор долга
     * @param retakeId идентификатор пересдачи
     * @return true если запись прошла успешно
     */
    override suspend fun enrollToRetake(studentId: Long, debtId: Long, retakeId: Long): Boolean {
        return KtorClient.enrollToRetake(studentId, debtId, retakeId)
    }

    /**
     * Отменяет запись студента на пересдачу.
     *
     * @param studentId идентификатор студента
     * @param debtId идентификатор долга
     * @param retakeId идентификатор пересдачи
     * @return true если отмена прошла успешно
     */
    override suspend fun cancelRetakeEnrollment(studentId: Long, debtId: Long, retakeId: Long): Boolean {
        return KtorClient.cancelRetakeEnrollment(studentId, debtId, retakeId)
    }

    /**
     * Создаёт комментарий к пересдаче от имени студента.
     *
     * @param studentId идентификатор студента
     * @param request данные комментария
     * @return созданный комментарий
     */
    override suspend fun createComment(studentId: Long, request: CreateCommentRequest): Comment {
        return KtorClient.createComment(studentId, request.toСreateCommentRequestDto()).toCommentDomain()
    }

    /**
     * Возвращает рейтинг студента по количеству долгов.
     *
     * @param studentId идентификатор студента
     * @return рейтинг студента
     */
    override suspend fun getStudentDebtRank(studentId: Long): StudentDebtRank {
        return KtorClient.getStudentDebtRank(studentId).toStudentDebtRankDomain()
    }
}