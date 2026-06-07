package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.data.remote.SettingsDataStore
import com.example.accountingofstudentretakesapp.domain.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository, private val settingsDataStore: SettingsDataStore) {
    suspend operator fun invoke() {
        authRepository.logout()
        settingsDataStore.clearUserData()
    }
}