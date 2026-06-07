package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.model.Teacher

/** Репозиторий для локального кэширования данных. */
interface LocalRepository {
    /** @param comments список комментариев для сохранения */
    suspend fun saveComments(comments: List<Comment>)

    /** @return список всех кэшированных комментариев */
    suspend fun getAllComments(): List<Comment>

    /** @param subjects список предметов для сохранения */
    suspend fun saveSubjects(subjects: List<Subject>)

    /** @return список всех кэшированных предметов */
    suspend fun getAllSubjects(): List<Subject>

    /** @param teachers список преподавателей для сохранения */
    suspend fun saveTeachers(teachers: List<Teacher>)

    /**
     * Возвращает преподавателей по дисциплине из кэша.
     * @param discipline название дисциплины
     */
    suspend fun getTeachersByDiscipline(discipline: String): List<Teacher>
}