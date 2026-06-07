package com.example.accountingofstudentretakesapp.domain.mapper.toDomain

import com.example.accountingofstudentretakesapp.data.model.RetakeDto
import com.example.accountingofstudentretakesapp.domain.model.Retake
import java.time.Instant

fun RetakeDto.toRetakeDomain() = Retake(
    id = id,
    type = type,
    subjectId = subjectId,
    place = place,
    admission = admission,
    startAt = Instant.parse(startAt),
    endAt = Instant.parse(endAt),
    lastModified = Instant.parse(lastModified),
    teacherIds = teacherIds
)