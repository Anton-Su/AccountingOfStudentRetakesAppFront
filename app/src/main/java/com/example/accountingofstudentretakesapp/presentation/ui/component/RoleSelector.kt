package com.example.accountingofstudentretakesapp.presentation.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.accountingofstudentretakesapp.domain.model.UserRole

/**
 * Селектор роли пользователя при входе в приложение.
 *
 * Отображает три кнопки — Student, Teacher, Admin — в одной строке.
 * Выбранная кнопка подсвечивается цветом роли:
 * - Student — зелёный
 * - Teacher — фиолетовый
 * - Admin — оранжевый
 *
 * Под селектором отображается карточка с возможностями выбранной роли:
 * - Student: запись на пересдачу, отмена, статистика, место в топе, отзывы
 * - Teacher: журнал пересдач
 * - Admin: управление пересдачами, просмотр отзывов
 *
 * @param selectedRole текущая выбранная роль
 * @param onRoleSelected колбэк при выборе роли
 */
@Composable
fun RoleSelector(selectedRole: UserRole, onRoleSelected: (UserRole) -> Unit) {
    val shape = RoundedCornerShape(14.dp)
    val borderColor = MaterialTheme.colorScheme.outline
    val studentColor = Color(0xFF4CAF50)
    val teacherColor = Color(0xFF9C27B0)
    val adminColor = Color(0xFFFF8C00)
    val textColorSelected = Color.White
    val textColorUnselected = MaterialTheme.colorScheme.onSurface
    val features = when (selectedRole) {
        UserRole.STUDENT -> listOf("Запись на пересдачу", "Отмена записи", "Статистика долгов", "Место в топе должников", "Оставить отзыв о пересдаче")
        UserRole.TEACHER -> listOf("Журнал пересдач")
        UserRole.ADMIN -> listOf("Полный контроль над пересдачами", "Просмотр отзывов")
        else -> {emptyList()}
    }
    val featureColor = when (selectedRole) {
        UserRole.STUDENT -> studentColor
        UserRole.TEACHER -> teacherColor
        UserRole.ADMIN -> adminColor
        else -> {textColorUnselected}
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            val itemWidth: Dp = maxWidth / 3
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .border(2.dp, borderColor, shape)
            ) {
                Box(
                    modifier = Modifier
                        .width(itemWidth)
                        .fillMaxHeight()
                        .background(if (selectedRole == UserRole.STUDENT) studentColor else Color.Transparent)
                        .clickable { onRoleSelected(UserRole.STUDENT) },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Student", color = if (selectedRole == UserRole.STUDENT) textColorSelected else textColorUnselected, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .width(itemWidth)
                        .fillMaxHeight()
                        .background(if (selectedRole == UserRole.TEACHER) teacherColor else Color.Transparent)
                        .clickable { onRoleSelected(UserRole.TEACHER) },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Teacher", color = if (selectedRole == UserRole.TEACHER) textColorSelected else textColorUnselected, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .width(itemWidth)
                        .fillMaxHeight()
                        .background(if (selectedRole == UserRole.ADMIN) adminColor else Color.Transparent)
                        .clickable { onRoleSelected(UserRole.ADMIN) },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Admin", color = if (selectedRole == UserRole.ADMIN) textColorSelected else textColorUnselected, fontWeight = FontWeight.Bold)
                }
            }
        }
        AnimatedVisibility(visible = true) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = CardDefaults.cardColors(containerColor = featureColor.copy(alpha = 0.1f)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    features.forEach { feature ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(featureColor, CircleShape)
                            )
                            Text(text = feature, style = MaterialTheme.typography.bodySmall, color = featureColor)
                        }
                    }
                }
            }
        }
    }
}