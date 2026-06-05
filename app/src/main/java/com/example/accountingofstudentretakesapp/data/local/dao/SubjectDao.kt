package com.example.accountingofstudentretakesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.accountingofstudentretakesapp.data.local.entity.SubjectEntity

@Dao
interface SubjectDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(subjects: List<SubjectEntity>)

    @Query("SELECT * FROM subjects")
    suspend fun getAll(): List<SubjectEntity>

    @Query("SELECT * FROM subjects WHERE id = :id")
    suspend fun getById(id: Long): SubjectEntity?
}