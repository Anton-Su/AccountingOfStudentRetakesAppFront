package com.example.accountingofstudentretakesapp.data.remote

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Хранилище токенов авторизации на основе DataStore.
 * Используется для сохранения JWT токена между сессиями.
 *
 * @param context контекст приложения
 */
val Context.tokenDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_tokens")

class TokenManager(private val context: Context) {

    private val ACCESS_TOKEN = stringPreferencesKey("access_token")

    /** Поток JWT токена. Возвращает null если токен не сохранён. */
    val accessTokenFlow: Flow<String?> = context.tokenDataStore.data
        .map { prefs -> prefs[ACCESS_TOKEN] }

    /**
     * Сохраняет JWT токен в хранилище.
     *
     * @param token JWT токен для сохранения
     */
    suspend fun saveAccessToken(token: String) {
        context.tokenDataStore.edit {
            it[ACCESS_TOKEN] = token
        }
    }

    /**
     * Очищает все токены из хранилища.
     * Вызывается при выходе из системы.
     */
    suspend fun clearTokens() {
        context.tokenDataStore.edit {
            it.clear()
        }
    }
}