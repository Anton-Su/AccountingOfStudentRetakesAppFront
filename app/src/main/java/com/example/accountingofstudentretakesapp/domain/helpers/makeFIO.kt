package com.example.accountingofstudentretakesapp.domain.helpers

/**
 * Форматирует ФИО в сокращённый вид: "Имя Отчество Ф."
 *
 * Пример: "Иван Иванович Иванов" → "Иван Иванович И."
 *
 * @param firstName имя
 * @param secondName отчество
 * @param lastName фамилия
 * @return сокращённое ФИО
 */
fun makeFIO(firstName: String, secondName: String, lastName: String) = firstName + " " + secondName + " " + lastName.take(1) + '.'