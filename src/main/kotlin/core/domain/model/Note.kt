package core.domain.model

import navigation.Argument
import java.time.LocalDateTime
import java.util.Calendar

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val created: Calendar
) : Argument {

    companion object {
        val EMPTY =  Note(
            id = null,
            title = "",
            content = "",
            created = Calendar.getInstance()
        )
    }
}