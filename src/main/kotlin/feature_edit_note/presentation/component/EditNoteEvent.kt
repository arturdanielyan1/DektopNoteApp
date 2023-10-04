package feature_edit_note.presentation.component

import core.component.UiEvent
import core.domain.model.Note

sealed interface EditNoteEvent : UiEvent {
    data object NavigateBack : EditNoteEvent
}