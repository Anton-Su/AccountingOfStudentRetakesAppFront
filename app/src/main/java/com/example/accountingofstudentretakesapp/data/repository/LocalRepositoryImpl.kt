package com.example.accountingofstudentretakesapp.data.repository

import android.content.Context
import com.example.accountingofstudentretakesapp.data.local.entity.CommentEntity
import com.example.accountingofstudentretakesapp.data.local.entity.SubjectEntity
import com.example.accountingofstudentretakesapp.data.local.entity.TeacherEntity
import com.example.accountingofstudentretakesapp.data.local.mapper.toCommentDomain
import com.example.accountingofstudentretakesapp.data.local.mapper.toCommentEntity
import com.example.accountingofstudentretakesapp.data.local.mapper.toSubjectDomain
import com.example.accountingofstudentretakesapp.data.local.mapper.toSubjectEntity
import com.example.accountingofstudentretakesapp.data.local.mapper.toTeacherDomain
import com.example.accountingofstudentretakesapp.data.local.mapper.toTeacherEntity
import com.example.accountingofstudentretakesapp.data.model.AppDatabase
import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

/**
 * Реализация локального репозитория для кэширования данных в Room.
 * Используется для offline-доступа при отсутствии сети.
 *
 * @param db экземпляр базы данных
 */
class LocalRepositoryImpl(private val db: AppDatabase) : LocalRepository {

    /**
     * Сохраняет список предметов в локальный кэш.
     *
     * @param subjects список предметов для сохранения
     */
    override suspend fun saveSubjects(subjects: List<Subject>) {
        val entities = subjects.map { it.toSubjectEntity() }
        db.subjectDao().insertAll(entities)
    }

    /**
     * Возвращает все предметы из локального кэша.
     *
     * @return список кэшированных предметов
     */
    override suspend fun getAllSubjects(): List<Subject> {
        return db.subjectDao().getAll().map { it.toSubjectDomain() }
    }

    /**
     * Сохраняет список преподавателей в локальный кэш.
     * Список дисциплин сохраняется как строка с разделителем "||".
     *
     * @param teachers список преподавателей для сохранения
     */
    override suspend fun saveTeachers(teachers: List<Teacher>) {
        val entities = teachers.map { it.toTeacherEntity()}
        db.teacherDao().insertAll(entities)
    }

    /**
     * Возвращает преподавателей по дисциплине из локального кэша.
     * Строка дисциплин разбивается обратно в список по разделителю "||".
     *
     * @param discipline название дисциплины
     * @return список преподавателей ведущих данную дисциплину
     */
    override suspend fun getTeachersByDiscipline(discipline: String): List<Teacher> {
        return db.teacherDao().getByDisciplineSimple(discipline).map { it.toTeacherDomain() }
    }

    /**
     * Сохраняет список комментариев в локальный кэш.
     *
     * @param comments список комментариев для сохранения
     */
    override suspend fun saveComments(comments: List<Comment>) {
        val entities = comments.map { it.toCommentEntity() }
        db.commentDao().insertAll(entities)
    }

    /**
     * Возвращает все комментарии из локального кэша.
     *
     * @return список кэшированных комментариев
     */
    override suspend fun getAllComments(): List<Comment> {
        return db.commentDao().getAll().map { it.toCommentDomain() }
    }

    companion object {
        @Volatile
        private var INSTANCE: LocalRepository? = null

        fun getInstance(context: Context): LocalRepository {
            return INSTANCE ?: synchronized(this) {
                val db = AppDatabase.getInstance(context)
                val instance = LocalRepositoryImpl(db)
                INSTANCE = instance
                instance
            }
        }
    }
}