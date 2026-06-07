package com.example.accountingofstudentretakesapp.data.repository

import android.content.Context
import com.example.accountingofstudentretakesapp.data.local.entity.CommentEntity
import com.example.accountingofstudentretakesapp.data.local.entity.SubjectEntity
import com.example.accountingofstudentretakesapp.data.local.entity.TeacherEntity
import com.example.accountingofstudentretakesapp.data.model.AppDatabase
import com.example.accountingofstudentretakesapp.domain.model.Comment
import com.example.accountingofstudentretakesapp.domain.model.Subject
import com.example.accountingofstudentretakesapp.domain.model.Teacher
import com.example.accountingofstudentretakesapp.domain.repository.LocalRepository

class LocalRepositoryImpl(private val db: AppDatabase): LocalRepository {

    override suspend fun saveSubjects(subjects: List<Subject>) {
        val entities = subjects.map { SubjectEntity(id = it.id, title = it.title) }
        db.subjectDao().insertAll(entities)
    }

    override suspend fun getAllSubjects(): List<Subject> {
        return db.subjectDao().getAll().map { Subject(id = it.id, title = it.title) }
    }

    override suspend fun saveTeachers(teachers: List<Teacher>) {
        val entities = teachers.map {
            TeacherEntity(
                userId = it.userId,
                fullName = it.fullName,
                disciplines = it.disciplines.joinToString(separator = "||")
            )
        }
        db.teacherDao().insertAll(entities)
    }

    override suspend fun getTeachersByDiscipline(discipline: String): List<Teacher> {
        val entities = db.teacherDao().getByDisciplineSimple(discipline)
        return entities.map {
            Teacher(
                userId = it.userId,
                fullName = it.fullName,
                disciplines = it.disciplines.split("||").filter { s -> s.isNotEmpty() })
        }
    }

    /**
     * Сохраняет список комментариев в локальный кеш.
     * @param comments список комментариев для сохранения
     */
    override suspend fun saveComments(comments: List<Comment>) {
        val entities = comments.map {
            CommentEntity(
                id = it.id,
                studentId = it.studentId,
                studentFullName = it.studentFullName,
                subjectTitle = it.subjectTitle,
                groupName = it.groupName,
                gradePlace = it.gradePlace,
                gradeTeacher = it.gradeTeacher,
                gradeOverall = it.gradeOverall,
                comment = it.comment,
                retakeId = it.retakeId,
                retakeStartAt = it.retakeStartAt,
                retakeEndAt = it.retakeEndAt
            )
        }
        db.commentDao().insertAll(entities)
    }

    /**
     * Получает все комментарии из локального кеша.
     * @return список всех кешированных комментариев
     */
    override suspend fun getAllComments(): List<Comment> {
        return db.commentDao().getAll().map {
            Comment(
                id = it.id,
                studentId = it.studentId,
                studentFullName = it.studentFullName,
                subjectTitle = it.subjectTitle,
                groupName = it.groupName,
                gradePlace = it.gradePlace,
                gradeTeacher = it.gradeTeacher,
                gradeOverall = it.gradeOverall,
                comment = it.comment,
                retakeId = it.retakeId,
                retakeStartAt = it.retakeStartAt,
                retakeEndAt = it.retakeEndAt
            )
        }
    }

    companion object {
        // гарантирует что все потоки видят актуальное значение INSTANCE
        @Volatile
        private var INSTANCE: LocalRepository? = null

        fun getInstance(context: Context): LocalRepository {
            // только один поток может войти сюда одновременно, чтобы не создать два объекта
            return INSTANCE ?: synchronized(this) {
                val db = AppDatabase.getInstance(context)
                val instance = LocalRepositoryImpl(db)
                INSTANCE = instance
                instance
            }
        }
    }
}