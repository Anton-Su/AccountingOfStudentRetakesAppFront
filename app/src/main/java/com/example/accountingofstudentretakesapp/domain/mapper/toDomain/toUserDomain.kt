package com.example.accountingofstudentretakesapp.domain.mapper.toDomain

import com.example.accountingofstudentretakesapp.data.model.UserDto
import com.example.accountingofstudentretakesapp.domain.model.User

fun UserDto.toUserDomain() = User(
    id = id,
    role = role,
    firstName = firstName,
    secondName = secondName,
    lastName = lastName,
    gender = gender,
    age = age,
    email = email,
)