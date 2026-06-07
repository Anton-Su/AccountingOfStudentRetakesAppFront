package com.example.accountingofstudentretakesapp.domain.model

/**
 * Запись студента на пересдачу.
 *
 * @property id уникальный идентификатор записи
 * @property retakeId идентификатор пересдачи
 * @property studentId идентификатор студента
 * @property studentSubjectId идентификатор долга студента по предмету
 * @property studentFullName полное имя студента
 * @property groupName название группы студента
 */
data class RetakeEnrollment(
    val id: Long,
    val retakeId: Long,
    val studentId: Long,
    val studentSubjectId: Long,
    val studentFullName: String,
    val groupName: String
)