package com.example.accountingofstudentretakesapp.presentation.ui.screen
import com.example.accountingofstudentretakesapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.accountingofstudentretakesapp.data.remote.TokenManager
import com.example.accountingofstudentretakesapp.data.repository.AuthRepositoryImpl
import com.example.accountingofstudentretakesapp.domain.usecase.LoginUseCase
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.ui.res.painterResource
import com.example.accountingofstudentretakesapp.presentation.model.UserRole
import com.example.accountingofstudentretakesapp.presentation.ui.component.RoleSelector
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("kuznetsova.i.a@edu.mirea.ru") }
    var password by remember { mutableStateOf("Teacher123!") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var role by remember { mutableStateOf(UserRole.STUDENT) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(  R.drawable.reklamatry),
            contentDescription = "Реклама",
            modifier = Modifier.size(200.dp)
        )
        Text("Вход в приложение", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        RoleSelector(
            selectedRole = role,
            onRoleSelected = { role = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { email = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Очистить email"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { password = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Очистить пароль"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                isLoading = true
                errorMessage = null
                scope.launch {
                    val repository = AuthRepositoryImpl(TokenManager(context))
                    val loginUseCase = LoginUseCase(repository)
                    loginUseCase(email, password, role)
                        .onSuccess { onLoginSuccess() }
                        .onFailure { errorMessage = it.message }
                    // "Попробуйте ещё"
                    isLoading = false
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Вход..." else "Войти")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Не получается войти? Уточните у ответственного лица университета.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Нашли ошибку в системе? Напишите разработчику на почту @develplover@mail.ru. Он, возможно, прочитает. Возможно...",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onErrorContainer
        )
        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}