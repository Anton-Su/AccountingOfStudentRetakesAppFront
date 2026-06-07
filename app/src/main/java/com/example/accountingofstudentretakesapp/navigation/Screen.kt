package com.example.accountingofstudentretakesapp.navigation

/**
 * Маршруты навигации приложения.
 * Каждый объект представляет отдельный экран с уникальным маршрутом.
 *
 * @property route строка маршрута для NavHost
 */
sealed class Screen(val route: String) {
    /** Экран авторизации */
    data object LoginScreen : Screen("login")

    /** Главный экран студента */
    data object StudentAllScreen : Screen("student_all_screen")

    /** Экран создания комментария к пересдаче */
    data object StudentCommentScreen : Screen("student_comment/{retakeId}") {
        /** @param retakeId идентификатор пересдачи */
        fun createRoute(retakeId: Long) = "student_comment/$retakeId"
    }

    /** Главный экран преподавателя */
    data object TeacherAllScreen : Screen("teacher_all_screen")

    /** Экран деталей пересдачи для преподавателя */
    data object TeacherRetakeDetailsScreen : Screen("teacher_retake_details/{retakeId}") {
        /** @param retakeId идентификатор пересдачи */
        fun createRoute(retakeId: Long) = "teacher_retake_details/$retakeId"
    }

    /** Главный экран администратора */
    data object AdminAllScreen : Screen("admin_all_screen")

    /** Экран создания пересдачи */
    data object AdminCreateRetakeScreen : Screen("admin_create_retake")

    /** Экран редактирования пересдачи */
    data object AdminRedactRetakeScreen : Screen("admin_redact_retake/{retakeId}") {
        /** @param retakeId идентификатор пересдачи */
        fun createRoute(retakeId: Long) = "admin_redact_retake/$retakeId"
    }
}