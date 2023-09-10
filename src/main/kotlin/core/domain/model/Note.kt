package core.domain.model

import navigation.Argument
import java.time.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val created: LocalDateTime
) : Argument
