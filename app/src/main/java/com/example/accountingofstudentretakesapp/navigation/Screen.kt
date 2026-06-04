package com.example.accountingofstudentretakesapp.navigation


sealed class Screen(val route: String) {
    data object LoginScreen : Screen("login")
    data object StudentAllScreen : Screen("student_all_screen")
    data object StudentCommentScreen : Screen("student_comment/{retakeId}") {
        fun createRoute(retakeId: Long) = "student_comment/$retakeId"
    }
    data object TeacherAllScreen : Screen("teacher_all_screen")
    data object AdminAllScreen : Screen("admin_all_screen")
    data object AdminCreateRetakeScreen : Screen("admin_create_retake")
    data object AdminRedactRetakeScreen : Screen("admin_redact_retake/{retakeId}") {
        fun createRoute(retakeId: Long) = "admin_redact_retake/$retakeId"
    }
    data object TeacherRetakeDetailsScreen : Screen("teacher_retake_details/{retakeId}") {
        fun createRoute(retakeId: Long) = "teacher_retake_details/$retakeId"
    }
}