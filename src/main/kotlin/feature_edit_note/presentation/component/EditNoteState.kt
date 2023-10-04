package feature_edit_note.presentation.component

import core.component.UiState
import core.domain.model.Note
import java.util.Calendar

data class EditNoteState(
    val note: Note
) : UiState {

    companion object {
        val EMPTY = EditNoteState(
            note = Note(
                id = null,
                title = "",
                content = "",
                created = Calendar.getInstance()
            )
        )
    }
}