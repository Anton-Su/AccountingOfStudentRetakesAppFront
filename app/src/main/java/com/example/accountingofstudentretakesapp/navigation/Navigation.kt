package com.example.accountingofstudentretakesapp.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.accountingofstudentretakesapp.data.remote.SettingsDataStore
import com.example.accountingofstudentretakesapp.presentation.ui.screen.AdminCreateRetakeScreen
import com.example.accountingofstudentretakesapp.presentation.ui.screen.AdminHomeScreen
import com.example.accountingofstudentretakesapp.presentation.ui.screen.AdminRedactRetakeScreen
import com.example.accountingofstudentretakesapp.presentation.ui.screen.LoginScreen
import com.example.accountingofstudentretakesapp.presentation.ui.screen.StudentCommentScreen
import com.example.accountingofstudentretakesapp.presentation.ui.screen.StudentHomeScreen
import com.example.accountingofstudentretakesapp.presentation.ui.screen.TeacherHomeScreen
import com.example.accountingofstudentretakesapp.presentation.ui.screen.TeacherRetakeDetailsScreen
import com.example.accountingofstudentretakesapp.presentation.viewmodel.RetakeViewModel

@Composable
fun Navigation(navController: NavHostController = rememberNavController(), viewModel: RetakeViewModel) {
    val context = LocalContext.current
    val settings = SettingsDataStore(context)
    val isLoggedIn by settings.isLoggedInFlow.collectAsState(initial = false)
    val role by settings.roleFlow.collectAsState(initial = "")
    val startDestination = remember(isLoggedIn, role) {
        if (isLoggedIn) {
            when (role) {
                "STUDENT" -> Screen.StudentAllScreen.route
                "TEACHER" -> Screen.TeacherAllScreen.route
                "ADMIN" -> Screen.AdminAllScreen.route
                else -> Screen.LoginScreen.route
            }
        }
        else
            Screen.LoginScreen.route
    }
    LaunchedEffect(isLoggedIn, role) {
        if (isLoggedIn && role.isNotEmpty()) {
            val target = when (role) {
                "STUDENT" -> Screen.StudentAllScreen.route
                "TEACHER" -> Screen.TeacherAllScreen.route
                "ADMIN" -> Screen.AdminAllScreen.route
                else -> Screen.LoginScreen.route
            }
            navController.navigate(target) {
                popUpTo(Screen.LoginScreen.route) { inclusive = true }
                launchSingleTop = true
            }
        }
    }
    val uiState = viewModel.uiState.collectAsState().value
    NavHost(navController, startDestination = startDestination) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(viewModel = viewModel)
        }
        composable(Screen.StudentAllScreen.route) {
            StudentHomeScreen(
                uiState = uiState,
                onLoadStudentData = {
                    viewModel.loadStudentDebts()
                    viewModel.loadStudentDebtRank()
                    viewModel.loadAvailableRetakes()
                    viewModel.loadEnrolledRetakes()
                },
                onRetakeClick = { retakeId ->
                    navController.navigate(Screen.StudentCommentScreen.createRoute(retakeId))
                },
                onEnrollRetake = { debtId, retakeId ->
                    viewModel.enrollToRetake(debtId = debtId, retakeId = retakeId, onSuccess = {}, onError = {})
                },
                onCancelRetake = { debtId, retakeId ->
                    viewModel.cancelRetakeEnrollment(debtId = debtId, retakeId = retakeId, onSuccess = {}, onError = {})
                },
                onLogout = {
                    viewModel.logout()
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(
            Screen.StudentCommentScreen.route,
            arguments = listOf(navArgument("retakeId") { type = NavType.LongType })
        ) { backStackEntry ->
            val retakeId = backStackEntry.arguments?.getLong("retakeId") ?: return@composable
            val uiState = uiState
            StudentCommentScreen(
                uiState = uiState,
                onSubmit = { gradePlace, gradeTeacher, gradeOverall, comment ->
                    viewModel.createComment(gradePlace = gradePlace, gradeTeacher = gradeTeacher, gradeOverall = gradeOverall, comment = comment, retakeId = retakeId, onSuccess = { navController.popBackStack() }, onError = {})
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.TeacherAllScreen.route) {
            TeacherHomeScreen(
                uiState = uiState,
                onLoadRetakes = { viewModel.loadTeacherRetakes() },
                onRetakeClick = { retakeId ->
                    navController.navigate(Screen.TeacherRetakeDetailsScreen.createRoute(retakeId))
                },
                onLogout = {
                    viewModel.logout()
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            Screen.TeacherRetakeDetailsScreen.route,
            arguments = listOf(navArgument("retakeId") { type = NavType.LongType })
        ) { backStackEntry ->
            val retakeId = backStackEntry.arguments?.getLong("retakeId") ?: return@composable
            TeacherRetakeDetailsScreen(
                retakeId = retakeId,
                uiState = uiState,
                onLoadRetakeDetails = { retakeId -> viewModel.loadTeacherRetakeDetails(retakeId) },
                onGradeStudent = { retakeId, studentId, score ->
                    viewModel.gradeStudent(retakeId, studentId, score)
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.AdminAllScreen.route) {
            AdminHomeScreen(
                uiState = uiState,
                onLoadRetakes = { viewModel.loadAllRetakes() },
                onLoadComments = { viewModel.loadAllComments() },
                onAddRetake = { navController.navigate(Screen.AdminCreateRetakeScreen.route) },
                onEditRetake = { retakeId -> navController.navigate(Screen.AdminRedactRetakeScreen.createRoute(retakeId)) },
                onDeleteRetake = { retakeId ->
                    viewModel.deleteRetake(retakeId, onSuccess = { navController.popBackStack() }, onError = { _ -> })
                },
                onLogout = {
                    viewModel.logout()
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(Screen.AdminCreateRetakeScreen.route) {
            AdminCreateRetakeScreen(
                uiState = uiState,
                onLoadSubjects = { viewModel.loadSubjects() },
                onLoadTeachers = { discipline ->
                    viewModel.loadTeachersByDiscipline(discipline)
                },
                onCreateRetake = { startAt, endAt, teacherIds, subjectId, type, place, admission ->
                    viewModel.createRetake(startAt = startAt, endAt = endAt, teacherIds = teacherIds, subjectId = subjectId, type = type, place = place, admission = admission, onSuccess = { navController.popBackStack() }, onError = { _ -> })
                },
                onClearTeachers = { viewModel.clearTeachersByDiscipline() },
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            Screen.AdminRedactRetakeScreen.route,
            arguments = listOf(navArgument("retakeId") { type = NavType.LongType })
        ) { backStackEntry ->
            val retakeId = backStackEntry.arguments?.getLong("retakeId") ?: return@composable
            AdminRedactRetakeScreen(
                retakeId = retakeId,
                uiState = uiState,
                onLoadSubjects = { viewModel.loadSubjects() },
                onLoadTeachers = { discipline ->
                    viewModel.loadTeachersByDiscipline(discipline)
                },
                onRedactRetake = { id, startAt, endAt, teacherIds, subjectId, type, place, admission ->
                    viewModel.redactRetake(retakeId = id, startAt = startAt, endAt = endAt, teacherIds = teacherIds, subjectId = subjectId, type = type, place = place, admission = admission, onSuccess = { navController.popBackStack() }, onError = { _ -> })
                },
                onClearTeachers = { viewModel.clearTeachersByDiscipline() },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
