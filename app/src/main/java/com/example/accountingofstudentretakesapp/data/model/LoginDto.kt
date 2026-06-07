package com.example.accountingofstudentretakesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val token: String
)
