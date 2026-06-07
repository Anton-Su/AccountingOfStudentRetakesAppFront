package com.example.accountingofstudentretakesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.accountingofstudentretakesapp.data.remote.KtorClient
import com.example.accountingofstudentretakesapp.navigation.Navigation
import com.example.accountingofstudentretakesapp.data.remote.SettingsDataStore
import com.example.accountingofstudentretakesapp.data.remote.TokenManager
import com.example.accountingofstudentretakesapp.data.repository.AdminRepositoryImpl
import com.example.accountingofstudentretakesapp.data.repository.AuthRepositoryImpl
import com.example.accountingofstudentretakesapp.data.repository.GuestRepositoryImpl
import com.example.accountingofstudentretakesapp.data.repository.LocalRepositoryImpl
import com.example.accountingofstudentretakesapp.data.repository.StudentRepositoryImpl
import com.example.accountingofstudentretakesapp.data.repository.TeacherRepositoryImpl
import com.example.accountingofstudentretakesapp.data.repository.UserRepositoryImpl
import com.example.accountingofstudentretakesapp.domain.usecase.CreateRetakeUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.CreateCommentUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.CancelRetakeEnrollmentUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.DeleteRetakeUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetAvailableRetakesUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetCurrentUserUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetEnrolledRetakesUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetStudentDebtRankUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetStudentDebtsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetTeacherRetakesUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetRetakeDetailsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GradeStudentUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.LoginUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetAllRetakesUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetSubjectsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.EnrollToRetakeUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetTeachersByDisciplineUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.RedactRetakeUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetAllCommentsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetCachedCommentsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetCachedSubjectsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.GetCachedTeachersByDisciplineUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.LogoutUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.SaveCommentsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.SaveSubjectsUseCase
import com.example.accountingofstudentretakesapp.domain.usecase.SaveTeachersUseCase
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeViewModel
import com.example.accountingofstudentretakesapp.ui.theme.AccountingOfStudentRetakesAppTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val tokenManager = TokenManager(applicationContext)
        // восстанавливаем токен при запуске приложения и устанавливаем его в KtorClient
        lifecycleScope.launch {
            tokenManager.accessTokenFlow.first()?.let { token ->
                KtorClient.updateAccessToken(token)
            }
        }
        val authRepository = AuthRepositoryImpl(tokenManager)
        val userRepository = UserRepositoryImpl()
        val getCurrentUserUseCase = GetCurrentUserUseCase(userRepository)
        val adminRepository = AdminRepositoryImpl()
        val studentRepository = StudentRepositoryImpl()
        val guestRepository = GuestRepositoryImpl()
        val settingsDataStore = SettingsDataStore(applicationContext)

        val logoutUseCase = LogoutUseCase(authRepository, settingsDataStore)
        val getAllRetakesUseCase = GetAllRetakesUseCase(adminRepository)
        val getSubjectsUseCase = GetSubjectsUseCase(guestRepository)
        val getTeachersByDisciplineUseCase = GetTeachersByDisciplineUseCase(adminRepository)
        val createRetakeUseCase = CreateRetakeUseCase(adminRepository)
        val deleteRetakeUseCase = DeleteRetakeUseCase(adminRepository)
        val redactRetakeUseCase = RedactRetakeUseCase(adminRepository)
        val getAllCommentsUseCase = GetAllCommentsUseCase(adminRepository)
        val getStudentDebtsUseCase = GetStudentDebtsUseCase(studentRepository)
        val getStudentDebtRankUseCase = GetStudentDebtRankUseCase(studentRepository)
        val getAvailableRetakesUseCase = GetAvailableRetakesUseCase(studentRepository)
        val getEnrolledRetakesUseCase = GetEnrolledRetakesUseCase(studentRepository)
        val enrollToRetakeUseCase = EnrollToRetakeUseCase(studentRepository)
        val cancelRetakeEnrollmentUseCase = CancelRetakeEnrollmentUseCase(studentRepository)
        val createCommentUseCase = CreateCommentUseCase(studentRepository)
        val teacherRepository = TeacherRepositoryImpl()
        val loginUseCase = LoginUseCase(authRepository)
        val getTeacherRetakesUseCase = GetTeacherRetakesUseCase(teacherRepository)
        val getRetakeDetailsUseCase = GetRetakeDetailsUseCase(teacherRepository)
        val gradeStudentUseCase = GradeStudentUseCase(teacherRepository)
        val localRepo = LocalRepositoryImpl.getInstance(applicationContext)

        val saveCommentsUseCase = SaveCommentsUseCase(localRepo)
        val getCachedCommentsUseCase = GetCachedCommentsUseCase(localRepo)
        val saveSubjectsUseCase = SaveSubjectsUseCase(localRepo)
        val getCachedSubjectsUseCase = GetCachedSubjectsUseCase(localRepo)
        val saveTeachersUseCase = SaveTeachersUseCase(localRepo)
        val getCachedTeachersByDisciplineUseCase = GetCachedTeachersByDisciplineUseCase(localRepo)

        val viewModel = RetakeViewModel(
            logoutUseCase = logoutUseCase,
            settingsDataStore = settingsDataStore,
            saveCommentsUseCase = saveCommentsUseCase,
            getCachedCommentsUseCase = getCachedCommentsUseCase,
            saveSubjectsUseCase = saveSubjectsUseCase,
            getCachedSubjectsUseCase = getCachedSubjectsUseCase,
            saveTeachersUseCase = saveTeachersUseCase,
            getCachedTeachersByDisciplineUseCase = getCachedTeachersByDisciplineUseCase,
            loginUseCase = loginUseCase,
            getCurrentUserUseCase = getCurrentUserUseCase,
            getTeacherRetakesUseCase = getTeacherRetakesUseCase,
            getRetakeDetailsUseCase = getRetakeDetailsUseCase,
            gradeStudentUseCase = gradeStudentUseCase,
            getAllRetakesUseCase = getAllRetakesUseCase,
            getSubjectsUseCase = getSubjectsUseCase,
            getTeachersByDisciplineUseCase = getTeachersByDisciplineUseCase,
            createRetakeUseCase = createRetakeUseCase,
            deleteRetakeUseCase = deleteRetakeUseCase,
            redactRetakeUseCase = redactRetakeUseCase,
            getAllCommentsUseCase = getAllCommentsUseCase,
            getStudentDebtsUseCase = getStudentDebtsUseCase,
            getStudentDebtRankUseCase = getStudentDebtRankUseCase,
            getAvailableRetakesUseCase = getAvailableRetakesUseCase,
            getEnrolledRetakesUseCase = getEnrolledRetakesUseCase,
            enrollToRetakeUseCase = enrollToRetakeUseCase,
            cancelRetakeEnrollmentUseCase = cancelRetakeEnrollmentUseCase,
            createCommentUseCase = createCommentUseCase,
        )
        setContent {
            AccountingOfStudentRetakesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                        Navigation(navController = rememberNavController(), viewModel = viewModel)
                    }
                }
            }
        }
    }
}