package core

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

fun LocalDateTime.toMillis() =
    this.toEpochSecond(ZoneOffset.of(ZoneOffset.systemDefault().id))

fun Long.toDateTime(): LocalDateTime =
    LocalDateTime.ofEpochSecond(this, 0, ZoneId.systemDefault().rules.getOffset(Instant.now()))
