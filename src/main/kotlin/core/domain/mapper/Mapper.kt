package core.domain.mapper

import com.example.notedesktop.NoteEntity
import core.toDateTime
import core.domain.model.Note
import java.util.*

fun NoteEntity.toNote() =
    Note(
        id = this.id,
        title = this.title,
        content = this.content,
        created = Calendar.getInstance().apply {
            timeInMillis = this@toNote.created
        }
    )