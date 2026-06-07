package com.example.accountingofstudentretakesapp.domain.repository

import com.example.accountingofstudentretakesapp.domain.model.Subject

interface GuestRepository{
    suspend fun getSubjects(): List<Subject>
}