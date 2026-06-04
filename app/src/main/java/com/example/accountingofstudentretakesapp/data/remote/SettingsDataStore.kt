package com.example.accountingofstudentretakesapp.data.remote

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.accountingofstudentretakesapp.domain.model.UserDto
import com.example.accountingofstudentretakesapp.presentation.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {
    companion object {
        val USER_ID = longPreferencesKey("user_id")
        val ROLE = stringPreferencesKey("role")

        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val FIRST_NAME = stringPreferencesKey("first_name")
        val SECOND_NAME = stringPreferencesKey("second_name")
        val LAST_NAME = stringPreferencesKey("last_name")
    }

    val userIdFlow: Flow<Long> = context.dataStore.data
        .map { prefs -> prefs[USER_ID] ?: -1L }

    val roleFlow: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[ROLE] ?: "" }

    val isLoggedInFlow: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[IS_LOGGED_IN] ?: false }

    val firstNameFlow: Flow<String> = context.dataStore.data
        .map { prefs: Preferences ->
            prefs[FIRST_NAME] ?: ""
        }

    val secondNameFlow: Flow<String> = context.dataStore.data
        .map { prefs: Preferences ->
            prefs[SECOND_NAME] ?: ""
        }

    val lastNameFlow: Flow<String> = context.dataStore.data
        .map { prefs: Preferences ->
            prefs[LAST_NAME] ?: ""
        }

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

    suspend fun clearUserData() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}