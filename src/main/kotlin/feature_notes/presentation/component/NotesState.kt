package feature_notes.presentation.component

import core.component.UiState
import core.domain.model.Note

data class NotesState(
    val isLoading: Boolean = true,
    val notes: List<Note> = emptyList()
) : UiState
