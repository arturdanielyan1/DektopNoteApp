package feature_edit_note.presentation

import core.ViewState
import core.domain.model.Note
import feature_edit_note.domain.usecases.SaveNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditNoteViewState(
    private val saveNoteUseCase: SaveNoteUseCase
) : ViewState<Note>() {

    private val _noteState = MutableStateFlow(Note.EMPTY)
    val noteState = _noteState.asStateFlow()

    override fun putInitialData(data: Note) {
        super.putInitialData(data)
        _noteState.value = data
    }

    fun saveNote(note: Note) {
        viewStateScope.launch {
            saveNoteUseCase(note)
        }
    }
}