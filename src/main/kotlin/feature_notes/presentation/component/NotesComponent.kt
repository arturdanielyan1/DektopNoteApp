package feature_notes.presentation.component

import com.arkivanov.decompose.ComponentContext
import core.component.BaseComponent
import core.domain.model.Note
import feature_notes.domain.usecases.DeleteNoteUseCase
import feature_notes.domain.usecases.GetAllNotesUseCase
import koin
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NotesComponent(
    componentContext: ComponentContext,
    private val navigateToEditNoteScreen: (Note) -> Unit
) : BaseComponent<NotesState, NotesEvent, NotesEffect, Nothing>(), ComponentContext by componentContext {

    private val getAllNotesUseCase: GetAllNotesUseCase = koin.get()
    private val deleteNoteUseCase: DeleteNoteUseCase = koin.get()

    init {
        componentScope.launch {
            getAllNotesUseCase().collectLatest {
                changeState {
                    this.copy(
                        isLoading = false,
                        notes = it
                    )
                }
            }
        }
    }
    override fun defineInitialState(): NotesState = NotesState()

    override fun handleEvent(event: NotesEvent) {
        when(event) {
            NotesEvent.CreateNote -> {}
            is NotesEvent.GoToNote -> {
                navigateToEditNoteScreen(event.note)
            }
            is NotesEvent.DeleteNote -> {
                componentScope.launch {
                    deleteNoteUseCase(event.id)
                }
            }
        }
    }
}