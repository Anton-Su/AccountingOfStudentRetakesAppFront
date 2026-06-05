package com.example.accountingofstudentretakesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.accountingofstudentretakesapp.data.local.entity.TeacherEntity

@Dao
interface TeacherDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(teachers: List<TeacherEntity>)

    @Query("SELECT * FROM teachers")
    suspend fun getAll(): List<TeacherEntity>

    @Query("SELECT * FROM teachers WHERE userId = :id")
    suspend fun getById(id: Long): TeacherEntity?

    @Query("SELECT * FROM teachers WHERE disciplines LIKE '%' || :discipline || '%' ")
    suspend fun getByDisciplineSimple(discipline: String): List<TeacherEntity>
}