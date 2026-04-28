package org.example.ordermanagementsystem.ui


import androidx.compose.ui.graphics.Color
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.math.round

fun Long.toFormattedDate(): String {
    val instant = Instant.fromEpochMilliseconds(this)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    val month = localDateTime.monthNumber.toString().padStart(2, '0')
    val day = localDateTime.dayOfMonth.toString().padStart(2, '0')
    val year = localDateTime.year

    val hour = localDateTime.hour.toString().padStart(2, '0')
    val minute = localDateTime.minute.toString().padStart(2, '0')

    return "$month/$day/$year $hour:$minute"
}

fun Double.toPriceString(): Double {
    val rounded = round(this * 100) / 100
    return rounded
}

fun stageColor(stage: String?): Color {
    return when (stage) {
//        "incoming" -> Color(0xFF6ba6d3)
        "in progress" -> Color(0xFFf4e477)
        "complete" -> Color(0xFFa6d36b)
        "canceled" -> Color(0xFFf9a4a4)
        else -> Color.LightGray
    }
}
