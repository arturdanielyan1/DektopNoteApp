package feature_notes.domain.usecases

import feature_notes.domain.data_source.NotesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DeleteNoteUseCase(
    private val notesDataSource: NotesDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(id: Long) =
        withContext(coroutineDispatcher) {
            notesDataSource.deleteNoteById(id)
        }
}