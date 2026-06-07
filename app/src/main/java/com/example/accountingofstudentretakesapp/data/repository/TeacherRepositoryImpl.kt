package com.example.accountingofstudentretakesapp.data.repository

import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.domain.mapper.toDto.toGradeRequestDto
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toRetakeDetailsDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toRetakeDomain
import com.example.accountingofstudentretakesapp.domain.mapper.toDomain.toRetakeEnrollmentDomain
import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.RetakeDetails
import com.example.accountingofstudentretakesapp.domain.model.RetakeEnrollment
import com.example.accountingofstudentretakesapp.domain.model.requests.GradeRequest
import com.example.accountingofstudentretakesapp.domain.repository.TeacherRepository

/**
 * Реализация репозитория для операций преподавателя.
 * Взаимодействует с сервером через [KtorClient].
 */
class TeacherRepositoryImpl : TeacherRepository {

    /**
     * Возвращает список пересдач преподавателя.
     *
     * @return список пересдач
     */
    override suspend fun getTeacherRetakes(): List<Retake> {
        return KtorClient.getTeacherRetakes().map { it.toRetakeDomain() }
    }

    /**
     * Возвращает детальную информацию о пересдаче.
     * Включает список записавшихся студентов.
     *
     * @param retakeId идентификатор пересдачи
     * @return детальная информация о пересдаче
     */
    override suspend fun getRetakeDetails(retakeId: Long): RetakeDetails {
        return KtorClient.getRetakeDetails(retakeId).toRetakeDetailsDomain()
    }

    /**
     * Выставляет оценку студенту на пересдаче.
     * После выставления оценки студент удаляется из списка записавшихся.
     *
     * @param retakeId идентификатор пересдачи
     * @param studentId идентификатор студента
     * @param request данные оценки
     * @return обновлённая запись студента на пересдачу
     */
    override suspend fun gradeStudent(retakeId: Long, studentId: Long, request: GradeRequest): RetakeEnrollment {
        return KtorClient.gradeStudent(retakeId, studentId, request.toGradeRequestDto()).toRetakeEnrollmentDomain()
    }
}