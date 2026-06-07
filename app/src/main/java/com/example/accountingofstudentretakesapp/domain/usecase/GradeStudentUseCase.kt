package com.example.accountingofstudentretakesapp.domain.usecase

import com.example.accountingofstudentretakesapp.domain.model.RetakeEnrollment
import com.example.accountingofstudentretakesapp.domain.model.requests.GradeRequest
import com.example.accountingofstudentretakesapp.domain.repository.TeacherRepository

/** Выставляет оценку студенту на пересдаче. */
class GradeStudentUseCase(private val teacherRepository: TeacherRepository) {
    /**
     * @param retakeId идентификатор пересдачи
     * @param studentId идентификатор студента
     * @param request данные оценки
     * @return обновлённая запись студента
     */
    suspend operator fun invoke(retakeId: Long, studentId: Long, request: GradeRequest): RetakeEnrollment {
        return teacherRepository.gradeStudent(retakeId, studentId, request)
    }
}
