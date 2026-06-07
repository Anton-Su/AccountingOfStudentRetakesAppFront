package com.example.accountingofstudentretakesapp.data.local.mapper

import com.example.accountingofstudentretakesapp.data.local.entity.CommentEntity
import com.example.accountingofstudentretakesapp.domain.model.Comment

/** Конвертирует domain модель [Comment] в Room entity [CommentEntity]. */
fun Comment.toCommentEntity() = CommentEntity(
    id = id,
    studentId = studentId,
    studentFullName = studentFullName,
    subjectTitle = subjectTitle,
    groupName = groupName,
    gradePlace = gradePlace,
    gradeTeacher = gradeTeacher,
    gradeOverall = gradeOverall,
    comment = comment,
    retakeId = retakeId,
    retakeStartAt = retakeStartAt,
    retakeEndAt = retakeEndAt
)
