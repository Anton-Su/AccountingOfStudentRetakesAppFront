package com.example.accountingofstudentretakesapp.domain.helpers

/**
 * Типы ошибок валидации формы отзыва о пересдаче.
 *
 * Используется как возвращаемый тип из [validate] вместо строки —
 * чтобы преобразование в локализованный текст через [stringResource]
 * происходило внутри Composable, а не в обычной функции.
 *
 * - [PLACE_REQUIRED] — оценка аудитории не введена
 * - [PLACE_RANGE] — оценка аудитории вне диапазона 0–10
 * - [TEACHER_REQUIRED] — оценка преподавателя не введена
 * - [TEACHER_RANGE] — оценка преподавателя вне диапазона 0–10
 * - [OVERALL_REQUIRED] — общая оценка не введена
 * - [OVERALL_RANGE] — общая оценка вне диапазона 0–100
 * - [COMMENT_LENGTH] — комментарий превышает 500 символов
 */

enum class ValidationError {
    PLACE_REQUIRED,
    PLACE_RANGE,
    TEACHER_REQUIRED,
    TEACHER_RANGE,
    OVERALL_REQUIRED,
    OVERALL_RANGE,
    COMMENT_LENGTH,
}