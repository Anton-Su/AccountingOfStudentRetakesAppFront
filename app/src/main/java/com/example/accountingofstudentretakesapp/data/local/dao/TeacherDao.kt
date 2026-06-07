package com.example.accountingofstudentretakesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.accountingofstudentretakesapp.data.local.entity.TeacherEntity

/**
 * DAO для работы с преподавателями в локальной базе данных.
 */
@Dao
interface TeacherDao {

    /**
     * Вставляет список преподавателей в базу данных.
     * Если преподаватель с таким userId уже существует — заменяет его.
     *
     * @param teachers список преподавателей для сохранения
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(teachers: List<TeacherEntity>)

    /**
     * Возвращает всех преподавателей из базы данных.
     *
     * @return список всех сохранённых преподавателей
     */
    @Query("SELECT * FROM teachers")
    suspend fun getAll(): List<TeacherEntity>

    /**
     * Возвращает преподавателя по его идентификатору.
     *
     * @param id идентификатор преподавателя
     * @return преподаватель с указанным userId или null если не найден
     */
    @Query("SELECT * FROM teachers WHERE userId = :id")
    suspend fun getById(id: Long): TeacherEntity?

    /**
     * Возвращает преподавателей у которых есть указанная дисциплина.
     * Поиск происходит по вхождению строки в поле disciplines.
     *
     * @param discipline название дисциплины для поиска
     * @return список преподавателей ведущих данную дисциплину
     */
    @Query("SELECT * FROM teachers WHERE disciplines LIKE '%' || :discipline || '%'")
    suspend fun getByDisciplineSimple(discipline: String): List<TeacherEntity>
}