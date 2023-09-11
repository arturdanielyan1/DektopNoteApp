package core

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun LocalDateTime.toMillis() =
    Calendar.getInstance().apply {
        set(Calendar.YEAR, this@toMillis.year)
        set(Calendar.MONTH, this@toMillis.monthValue)
        set(Calendar.DAY_OF_MONTH, this@toMillis.dayOfMonth)
        set(Calendar.HOUR_OF_DAY, this@toMillis.hour)
        set(Calendar.MINUTE, this@toMillis.minute)
        set(Calendar.SECOND, this@toMillis.second)
    }.timeInMillis

fun Long.toDateTime(): LocalDateTime =
    LocalDateTime.ofEpochSecond(this, 0, ZoneId.systemDefault().rules.getOffset(Instant.now()))
