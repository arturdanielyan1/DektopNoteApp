package feature_notes.presentation.component

import core.component.UiEvent
import core.domain.model.Note

sealed interface NotesEvent : UiEvent {
    data object CreateNote : NotesEvent
    class GoToNote(val note: Note) : NotesEvent

    class DeleteNote(val id: Long) : NotesEvent
}