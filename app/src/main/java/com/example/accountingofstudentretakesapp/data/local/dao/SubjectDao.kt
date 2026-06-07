package com.example.accountingofstudentretakesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.accountingofstudentretakesapp.data.local.entity.SubjectEntity

/**
 * DAO для работы с предметами в локальной базе данных.
 */
@Dao
interface SubjectDao {

    /**
     * Вставляет список предметов в базу данных.
     * Если предмет с таким id уже существует — заменяет его.
     *
     * @param subjects список предметов для сохранения
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(subjects: List<SubjectEntity>)

    /**
     * Возвращает все предметы из базы данных.
     *
     * @return список всех сохранённых предметов
     */
    @Query("SELECT * FROM subjects")
    suspend fun getAll(): List<SubjectEntity>

    /**
     * Возвращает предмет по его идентификатору.
     *
     * @param id идентификатор предмета
     * @return предмет с указанным id или null если не найден
     */
    @Query("SELECT * FROM subjects WHERE id = :id")
    suspend fun getById(id: Long): SubjectEntity?
}