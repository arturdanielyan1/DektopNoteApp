package feature_notes.presentation.component

import core.component.UiEvent

sealed interface NotesEvent : UiEvent {
    data object CreateNote : NotesEvent
    class GoToNote(val id: Long) : NotesEvent

    class DeleteNote(val id: Long) : NotesEvent
}