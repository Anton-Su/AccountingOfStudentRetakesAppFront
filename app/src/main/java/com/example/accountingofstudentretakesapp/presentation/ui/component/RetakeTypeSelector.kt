package com.example.accountingofstudentretakesapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.accountingofstudentretakesapp.R

/**
 * Селектор типа пересдачи — "Экзамен" или "Зачёт".
 *
 * Отображает две кнопки в одной строке. Выбранная кнопка подсвечивается
 * цветом: "Экзамен" — primary, "Зачёт" — secondary.
 *
 * @param selectedType текущий выбранный тип (null — ничего не выбрано)
 * @param onTypeSelected колбэк при выборе типа
 * @param modifier модификатор
 */

@Composable
fun RetakeTypeSelector(selectedType: String?, onTypeSelected: (String) -> Unit, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(14.dp)
    val borderColor = MaterialTheme.colorScheme.outline
    val examColor = MaterialTheme.colorScheme.primary
    val passColor = MaterialTheme.colorScheme.secondary
    val textColorSelected = Color.White
    val textColorUnselected = MaterialTheme.colorScheme.onSurface
    val examLabel = stringResource(R.string.retake_type_exam)
    val passLabel = stringResource(R.string.retake_type_pass)
    val options = listOf(examLabel, passLabel)
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        val itemWidth: Dp = maxWidth / options.size
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .border(2.dp, borderColor, shape)
        ) {
            options.forEach { option ->
                val selected = selectedType == option
                Box(
                    modifier = Modifier
                        .width(itemWidth)
                        .fillMaxHeight()
                        .background(
                            when {
                                selected && option == examLabel -> examColor
                                selected && option == passLabel -> passColor
                                else -> Color.Transparent
                            }
                        )
                        .clickable { onTypeSelected(option) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = option, color = if (selected) textColorSelected else textColorUnselected, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}