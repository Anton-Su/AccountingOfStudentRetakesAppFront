package com.example.accountingofstudentretakesapp.domain.mapper.toDto

import com.example.accountingofstudentretakesapp.data.model.requests.LoginRequestDto
import com.example.accountingofstudentretakesapp.domain.model.requests.LoginRequest

fun LoginRequest.toLoginRequestDto() = LoginRequestDto(
    email = email,
    password = password
)