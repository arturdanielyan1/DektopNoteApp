package core.domain.model

import navigation.Argument
import java.time.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val created: LocalDateTime
) : Argument {

    companion object {
        val EMPTY =  Note(
            id = null,
            title = "",
            content = "",
            created = LocalDateTime.MIN
        )
    }
}