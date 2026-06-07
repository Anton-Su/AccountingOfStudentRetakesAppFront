package com.example.accountingofstudentretakesapp.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.accountingofstudentretakesapp.R
import com.example.accountingofstudentretakesapp.domain.model.UserRole
import com.example.accountingofstudentretakesapp.presentation.ui.component.RoleSelector
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeUiState
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeViewModel

/**
 * Экран входа в приложение.
 *
 * Содержит:
 * - [RoleSelector] для ознакомления с возможностями каждой роли
 * - Поля ввода email и пароля с кнопкой очистки
 * - Кнопка входа — заблокирована пока поля пустые или идёт загрузка
 * - Сообщение об ошибке если логин не удался
 *
 * Роль определяется автоматически на сервере по email —
 * [RoleSelector] здесь только информационный, не влияет на логин.
 *
 * Кнопка "Войти" активна только когда:
 * - email и пароль не пустые
 * - не идёт загрузка ([RetakeUiState.isLoading] = false)
 *
 * @param viewModel ViewModel для вызова [RetakeViewModel.login]
 * @param uiState UI стейт — состояние загрузки и сообщение об ошибке
 */
@Composable
fun LoginScreen(viewModel: RetakeViewModel, uiState: RetakeUiState) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf(UserRole.NONE) }

    //petrov.m.i@edu.mirea.ru
    // Student123!

    // kuznetsova.i.a@edu.mirea.ru
    // Teacher123!

    // volkov.a.s@edu.mirea.ru
    // Admin123!

    val isLoginEnabled = !uiState.isLoading && email.isNotBlank() && password.isNotBlank()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.role_selector_title), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        RoleSelector(
            selectedRole = role,
            onRoleSelected = { role = it }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(stringResource(R.string.login_title), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.login_email_label)) },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { email = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = stringResource(R.string.login_clear_email_cd)
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.login_password_label)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { password = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = stringResource(R.string.login_clear_password_cd)
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { viewModel.login(email, password) },
            modifier = Modifier.fillMaxWidth(),
            enabled = isLoginEnabled
        ) {
            Text(if (uiState.isLoading) stringResource(R.string.login_loading) else stringResource(R.string.login_submit))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(R.string.login_help_university), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(R.string.login_help_developer), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onErrorContainer)
        uiState.errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}