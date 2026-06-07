package com.example.accountingofstudentretakesapp.data.remote

import com.example.accountingofstudentretakesapp.data.model.CommentDto
import com.example.accountingofstudentretakesapp.data.model.requests.CreateCommentRequestDto
import com.example.accountingofstudentretakesapp.data.model.LoginDto
import com.example.accountingofstudentretakesapp.data.model.RetakeDetailsDto
import com.example.accountingofstudentretakesapp.data.model.RetakeDto
import com.example.accountingofstudentretakesapp.data.model.RetakeEnrollmentDto
import com.example.accountingofstudentretakesapp.data.model.StudentDebtDto
import com.example.accountingofstudentretakesapp.data.model.StudentDebtRankDto
import com.example.accountingofstudentretakesapp.data.model.SubjectDto
import com.example.accountingofstudentretakesapp.data.model.TeacherDto
import com.example.accountingofstudentretakesapp.data.model.UserDto
import com.example.accountingofstudentretakesapp.data.model.requests.CreateRetakeRequestDto
import com.example.accountingofstudentretakesapp.data.model.requests.GradeRequestDto
import com.example.accountingofstudentretakesapp.data.model.requests.LoginRequestDto
import com.example.accountingofstudentretakesapp.data.model.requests.RedactRetakeRequestDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json



/**
 * HTTP клиент для взаимодействия с сервером.
 * Использует Ktor с OkHttp движком.
 *
 * Поддерживает Bearer токен авторизацию —
 * токен автоматически добавляется ко всем запросам на /api/
 */


object KtorClient {
    //private const val BASE_URL = "http://10.0.2.2:8080"
    //private const val BASE_URL = "http://192.168.31.66:8080"
    // private const val BASE_URL = "http://192.168.1.141:8080"

    private const val BASE_URL = "https://historius.stranik.dev"

    private var currentAccessToken: String? = null

    private fun buildClient() = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true  // игнорируем неизвестные поля от сервера
                isLenient = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
            connectTimeoutMillis = 15000
            socketTimeoutMillis = 15000
        }
        install(Auth) {
            bearer {
                loadTokens {
                    currentAccessToken?.let { BearerTokens(it, "") }
                }
                sendWithoutRequest { request ->
                    request.url.build().encodedPath.startsWith("/api")
                }
            }
        }
        defaultRequest {
            url(BASE_URL)
        }
    }

    private var client = buildClient()

    /**
     * Обновляет токен авторизации и пересоздаёт HTTP клиент.
     *
     * @param token новый JWT токен или null для сброса
     */
    fun updateAccessToken(token: String?) {
        currentAccessToken = token
        client = buildClient()
    }

    /**
     * Сбрасывает токен авторизации и пересоздаёт HTTP клиент.
     * Вызывается при выходе из системы.
     */
    fun clearTokens() {
        currentAccessToken = null
        client = buildClient()
    }

    /**
     * Авторизует пользователя и возвращает JWT токен.
     *
     * @param request данные для входа (email и пароль)
     * @return DTO с JWT токеном
     */
    suspend fun login(request: LoginRequestDto): LoginDto {
        return client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /**
     * Возвращает профиль текущего авторизованного пользователя.
     *
     * @return DTO пользователя
     */
    suspend fun getProfile(): UserDto {
        return client.get("/api/users/me").body()
    }

    /**
     * Возвращает список преподавателей по дисциплине.
     *
     * @param discipline название дисциплины
     * @return список DTO преподавателей
     */
    suspend fun getTeachersByDiscipline(discipline: String): List<TeacherDto> {
        return client.get("/api/admin/teachers") {
            parameter("discipline", discipline)
        }.body()
    }

    /**
     * Возвращает список всех предметов.
     *
     * @return список DTO предметов
     */
    suspend fun getSubjects(): List<SubjectDto> {
        return client.get("/general/subjects").body()
    }

    /**
     * Создаёт новую пересдачу.
     *
     * @param request DTO с данными новой пересдачи
     * @return DTO созданной пересдачи
     */
    suspend fun createRetake(request: CreateRetakeRequestDto): RetakeDto {
        return client.post("/api/admin/create_retake") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /**
     * Обновляет существующую пересдачу.
     *
     * @param id идентификатор пересдачи
     * @param request DTO с обновлёнными данными
     * @return DTO обновлённой пересдачи
     */
    suspend fun updateRetake(id: Long, request: RedactRetakeRequestDto): RetakeDto {
        return client.put("/api/admin/retakes/$id") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /**
     * Удаляет пересдачу по идентификатору.
     *
     * @param id идентификатор пересдачи
     */
    suspend fun deleteRetake(id: Long) {
        client.delete("/api/admin/retakes/$id")
    }

    /**
     * Возвращает все комментарии.
     *
     * @return список DTO комментариев
     */
    suspend fun getAllComments(): List<CommentDto> {
        return client.get("/api/admin/comments").body()
    }

    /**
     * Возвращает все пересдачи.
     *
     * @return список DTO пересдач
     */
    suspend fun getAllRetakes(): List<RetakeDto> {
        return client.get("/api/admin/retakes").body()
    }

    /**
     * Возвращает список долгов студента.
     *
     * @param studentId идентификатор студента
     * @return список DTO долгов
     */
    suspend fun getStudentDebts(studentId: Long): List<StudentDebtDto> {
        return client.get("/api/student/$studentId/debts").body()
    }

    /**
     * Возвращает список доступных пересдач для студента.
     *
     * @param studentId идентификатор студента
     * @return список DTO доступных пересдач
     */
    suspend fun getAvailableRetakes(studentId: Long): List<RetakeDto> {
        return client.get("/api/student/$studentId/retakes/available").body()
    }

    /**
     * Возвращает список пересдач на которые записан студент.
     *
     * @param studentId идентификатор студента
     * @return список DTO пересдач
     */
    suspend fun getEnrolledRetakes(studentId: Long): List<RetakeDto> {
        return client.get("/api/student/$studentId/retakes/enrolled").body()
    }

    /**
     * Записывает студента на пересдачу.
     *
     * @param studentId идентификатор студента
     * @param debtId идентификатор долга
     * @param retakeId идентификатор пересдачи
     * @return true если запись прошла успешно
     */
    suspend fun enrollToRetake(studentId: Long, debtId: Long, retakeId: Long): Boolean {
        return client.post("/api/student/$studentId/debts/$debtId/retakes/$retakeId").body()
    }

    /**
     * Отменяет запись студента на пересдачу.
     *
     * @param studentId идентификатор студента
     * @param debtId идентификатор долга
     * @param retakeId идентификатор пересдачи
     * @return true если отмена прошла успешно
     */
    suspend fun cancelRetakeEnrollment(studentId: Long, debtId: Long, retakeId: Long): Boolean {
        return client.delete("/api/student/$studentId/debts/$debtId/retakes/$retakeId").body()
    }

    /**
     * Создаёт комментарий к пересдаче от имени студента.
     *
     * @param studentId идентификатор студента
     * @param request DTO с данными комментария
     * @return DTO созданного комментария
     */
    suspend fun createComment(studentId: Long, request: CreateCommentRequestDto): CommentDto {
        return client.post("/api/student/$studentId/comments") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /**
     * Возвращает рейтинг студента по количеству долгов.
     *
     * @param studentId идентификатор студента
     * @return DTO рейтинга студента
     */
    suspend fun getStudentDebtRank(studentId: Long): StudentDebtRankDto {
        return client.get("/api/student/$studentId/debts/rank").body()
    }

    /**
     * Возвращает список пересдач преподавателя.
     *
     * @return список DTO пересдач
     */
    suspend fun getTeacherRetakes(): List<RetakeDto> {
        return client.get("/api/teacher/retakes").body()
    }

    /**
     * Возвращает детальную информацию о пересдаче для преподавателя.
     *
     * @param retakeId идентификатор пересдачи
     * @return DTO с деталями пересдачи и списком записавшихся студентов
     */
    suspend fun getRetakeDetails(retakeId: Long): RetakeDetailsDto {
        return client.get("/api/teacher/retake/$retakeId").body()
    }

    /**
     * Выставляет оценку студенту на пересдаче.
     *
     * @param retakeId идентификатор пересдачи
     * @param studentId идентификатор студента
     * @param request DTO с оценкой
     * @return DTO обновлённой записи студента
     */
    suspend fun gradeStudent(retakeId: Long, studentId: Long, request: GradeRequestDto): RetakeEnrollmentDto {
        return client.post("/api/teacher/retake/$retakeId/student/$studentId/grade") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}