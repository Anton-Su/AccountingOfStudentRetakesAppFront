package com.example.accountingofstudentretakesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.accountingofstudentretakesapp.data.local.entity.CommentEntity

/**
 * DAO для работы с комментариями студентов о пересдачах.
 * Поддерживает вставку, удаление и получение комментариев из локального кеша.
 */
@Dao
interface CommentDao {
    /**
     * Вставляет или обновляет список комментариев. В случае конфликта запись заменяется.
     * @param comments список сущностей комментариев для сохранения
     */
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(comments: List<CommentEntity>)

    /**
     * Возвращает все сохранённые комментарии из кеша.
     * @return список всех комментариев
     */
    @Query("SELECT * FROM comments")
    suspend fun getAll(): List<CommentEntity>
}