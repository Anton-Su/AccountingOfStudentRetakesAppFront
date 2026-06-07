package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.StudentDebt
import com.example.accountingofstudentretakesapp.domain.model.StudentDebtRank
import com.example.accountingofstudentretakesapp.domain.model.requests.CreateCommentRequest

/** Репозиторий для операций студента. */
interface StudentRepository {
    /**
     * Возвращает список долгов студента.
     * @param studentId идентификатор студента
     */
    suspend fun findDebtsByStudentId(studentId: Long): List<StudentDebt>

    /**
     * Возвращает список доступных пересдач для студента.
     * @param studentId идентификатор студента
     */
    suspend fun findAvailableRetakes(studentId: Long): List<Retake>

    /**
     * Возвращает список пересдач на которые записан студент.
     * @param studentId идентификатор студента
     */
    suspend fun findEnrolledRetakes(studentId: Long): List<Retake>

    /**
     * Записывает студента на пересдачу.
     * @param studentId идентификатор студента
     * @param debtId идентификатор долга
     * @param retakeId идентификатор пересдачи
     * @return true если запись прошла успешно
     */
    suspend fun enrollToRetake(studentId: Long, debtId: Long, retakeId: Long): Boolean

    /**
     * Отменяет запись студента на пересдачу.
     * @param studentId идентификатор студента
     * @param debtId идентификатор долга
     * @param retakeId идентификатор пересдачи
     * @return true если отмена прошла успешно
     */
    suspend fun cancelRetakeEnrollment(studentId: Long, debtId: Long, retakeId: Long): Boolean

    /**
     * Создаёт комментарий к пересдаче от имени студента.
     * @param studentId идентификатор студента
     * @param request данные комментария
     * @return созданный комментарий
     */
    suspend fun createComment(studentId: Long, request: CreateCommentRequest): Comment

    /**
     * Возвращает рейтинг студента по количеству долгов.
     * @param studentId идентификатор студента
     */
    suspend fun getStudentDebtRank(studentId: Long): StudentDebtRank
}
