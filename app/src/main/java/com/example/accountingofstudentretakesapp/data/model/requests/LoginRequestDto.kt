package com.example.accountingofstudentretakesapp.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val email: String,
    val password: String
)
