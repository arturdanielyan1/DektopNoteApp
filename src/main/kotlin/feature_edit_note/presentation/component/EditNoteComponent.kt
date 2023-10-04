package feature_edit_note.presentation.component

import com.arkivanov.decompose.ComponentContext
import core.component.BaseComponent
import core.domain.model.Note
import feature_edit_note.domain.usecases.SaveNoteUseCase
import koin
import kotlinx.coroutines.launch

class EditNoteComponent(
    componentContext: ComponentContext,
    private val navigateBack: () -> Unit
) : BaseComponent<EditNoteState, EditNoteEvent, EditNoteEffect, Note>(), ComponentContext by componentContext {

    private val saveNoteUseCase: SaveNoteUseCase = koin.get()

    override fun putInitialData(data: Note) {
        super.putInitialData(data)
        changeState {
            copy(
                note = note
            )
        }
    }

    override fun defineInitialState(): EditNoteState = EditNoteState.EMPTY

    override fun handleEvent(event: EditNoteEvent) {
        when(event) {
            EditNoteEvent.NavigateBack -> {
                componentScope.launch {
                    saveNoteUseCase(currentState.note)
                }
                navigateBack()
            }
        }
    }
}