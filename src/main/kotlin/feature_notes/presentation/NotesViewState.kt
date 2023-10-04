package feature_notes.presentation

import core.ViewState
import feature_notes.domain.usecases.DeleteNoteUseCase
import feature_notes.domain.usecases.GetAllNotesUseCase
import feature_notes.presentation.component.NotesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NotesViewState(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewState<Nothing>() {

    private val _notes = MutableStateFlow(NotesState())
    val notes = _notes.asStateFlow()

    init {
        viewStateScope.launch {
            getAllNotesUseCase().collectLatest {
                _notes.emit(NotesState(false, it))
            }
        }
    }

    fun deleteNoteById(id: Long) {
        viewStateScope.launch {
            deleteNoteUseCase(id)
        }
    }
}