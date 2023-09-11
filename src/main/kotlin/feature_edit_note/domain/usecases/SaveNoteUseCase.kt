package feature_edit_note.domain.usecases

import core.domain.model.Note
import feature_edit_note.domain.data_source.EditNoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SaveNoteUseCase(
    private val editNoteDataSource: EditNoteDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(note: Note) =
        withContext(coroutineDispatcher) {
            editNoteDataSource.saveNote(note)
        }
}