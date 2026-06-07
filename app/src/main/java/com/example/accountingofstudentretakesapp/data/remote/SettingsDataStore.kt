package com.example.accountingofstudentretakesapp.data.remote

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.accountingofstudentretakesapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Хранилище настроек и данных пользователя на основе DataStore.
 * Используется для сохранения профиля пользователя между сессиями.
 *
 * @param context контекст приложения
 */
private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {

    companion object {
        /** Идентификатор пользователя */
        val USER_ID = longPreferencesKey("user_id")

        /** Роль пользователя в системе */
        val ROLE = stringPreferencesKey("role")

        /** Флаг авторизации пользователя */
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

        /** Имя пользователя */
        val FIRST_NAME = stringPreferencesKey("first_name")

        /** Отчество пользователя */
        val SECOND_NAME = stringPreferencesKey("second_name")

        /** Фамилия пользователя */
        val LAST_NAME = stringPreferencesKey("last_name")
    }

    /** Поток идентификатора пользователя. Возвращает -1 если не задан. */
    val userIdFlow: Flow<Long> = context.dataStore.data
        .map { prefs -> prefs[USER_ID] ?: -1L }

    /** Поток роли пользователя. Возвращает пустую строку если не задана. */
    val roleFlow: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[ROLE] ?: "" }

    /** Поток флага авторизации. Возвращает false если не задан. */
    val isLoggedInFlow: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[IS_LOGGED_IN] ?: false }

    /** Поток имени пользователя. Возвращает пустую строку если не задано. */
    val firstNameFlow: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[FIRST_NAME] ?: "" }

    /** Поток отчества пользователя. Возвращает пустую строку если не задано. */
    val secondNameFlow: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[SECOND_NAME] ?: "" }

    /** Поток фамилии пользователя. Возвращает пустую строку если не задана. */
    val lastNameFlow: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[LAST_NAME] ?: "" }

    /**
     * Сохраняет профиль пользователя в хранилище.
     * Устанавливает флаг авторизации в true.
     *
     * @param user пользователь данные которого нужно сохранить
     */
    suspend fun saveUserProfile(user: User) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID] = user.id
            prefs[ROLE] = user.role.name
            prefs[IS_LOGGED_IN] = true
            prefs[FIRST_NAME] = user.firstName
            prefs[SECOND_NAME] = user.secondName
            prefs[LAST_NAME] = user.lastName
        }
    }

    /**
     * Очищает все данные пользователя из хранилища.
     * Вызывается при выходе из системы.
     */
    suspend fun clearUserData() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}