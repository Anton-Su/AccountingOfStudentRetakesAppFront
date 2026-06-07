package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.Retake
import com.example.accountingofstudentretakesapp.domain.model.RetakeDetails
import com.example.accountingofstudentretakesapp.domain.model.RetakeEnrollment
import com.example.accountingofstudentretakesapp.domain.model.requests.GradeRequest

/** Репозиторий для операций преподавателя. */
interface TeacherRepository {
	/** Возвращает список пересдач преподавателя. */
	suspend fun getTeacherRetakes(): List<Retake>

	/**
	 * Возвращает детальную информацию о пересдаче.
	 * @param retakeId идентификатор пересдачи
	 */
	suspend fun getRetakeDetails(retakeId: Long): RetakeDetails

	/**
	 * Выставляет оценку студенту на пересдаче.
	 * @param retakeId идентификатор пересдачи
	 * @param studentId идентификатор студента
	 * @param request данные оценки
	 * @return обновлённая запись студента
	 */
	suspend fun gradeStudent(retakeId: Long, studentId: Long, request: GradeRequest): RetakeEnrollment
}


