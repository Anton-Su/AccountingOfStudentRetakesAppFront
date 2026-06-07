package com.example.accountingofstudentretakesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность предмета в локальной базе данных.
 *
 * @property id уникальный идентификатор предмета
 * @property title название предмета
 */
@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey val id: Long,
    val title: String
)