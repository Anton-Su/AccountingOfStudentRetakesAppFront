package com.example.accountingofstudentretakesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teachers")
data class TeacherEntity(
    @PrimaryKey val userId: Long,
    val fullName: String,
    val disciplines: String
)