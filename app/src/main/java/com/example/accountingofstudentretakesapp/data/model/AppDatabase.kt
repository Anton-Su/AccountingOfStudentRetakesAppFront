package com.example.accountingofstudentretakesapp.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.accountingofstudentretakesapp.data.local.dao.CommentDao
import com.example.accountingofstudentretakesapp.data.local.entity.CommentEntity
import com.example.accountingofstudentretakesapp.data.local.dao.SubjectDao
import com.example.accountingofstudentretakesapp.data.local.entity.SubjectEntity
import com.example.accountingofstudentretakesapp.data.local.dao.TeacherDao
import com.example.accountingofstudentretakesapp.data.local.entity.TeacherEntity

@Database(entities = [SubjectEntity::class, TeacherEntity::class, CommentEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Возвращает DAO для работы с предметами.
     */
    abstract fun subjectDao(): SubjectDao

    /**
     * Возвращает DAO для работы с преподавателями.
     */
    abstract fun teacherDao(): TeacherDao

    /**
     * Возвращает DAO для работы с комментариями.
     */
    abstract fun commentDao(): CommentDao

    companion object {
        private const val DB_NAME = "app_cache.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Возвращает синглтон экземпляра базы данных.
         * @param context контекст приложения
         * @return экземпляр AppDatabase
         */
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
