package core

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


fun Long.toDateTime(): LocalDateTime =
    LocalDateTime.ofEpochSecond(this, 0, ZoneId.systemDefault().rules.getOffset(Instant.now()))
