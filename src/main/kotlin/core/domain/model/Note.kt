package core.domain.model

import java.io.Serializable
import java.util.Calendar

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val created: Calendar
) : Serializable {

    companion object {
        val EMPTY =  Note(
            id = null,
            title = "",
            content = "",
            created = Calendar.getInstance()
        )
    }
}