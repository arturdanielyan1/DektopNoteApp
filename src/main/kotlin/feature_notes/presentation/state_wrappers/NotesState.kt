package feature_notes.presentation.state_wrappers

import core.domain.model.Note

data class NotesState(
    val isLoading: Boolean = true,
    val notes: List<Note> = emptyList()
)
