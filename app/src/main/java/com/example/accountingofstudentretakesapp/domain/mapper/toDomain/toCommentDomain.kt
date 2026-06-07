package com.example.accountingofstudentretakesapp.domain.mapper.toDomain

import com.example.accountingofstudentretakesapp.data.model.CommentDto
import com.example.accountingofstudentretakesapp.domain.model.Comment


fun CommentDto.toCommentDomain() = Comment(
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
    retakeEndAt = retakeEndAt,
)
