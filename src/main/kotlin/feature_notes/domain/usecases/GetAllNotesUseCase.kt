package feature_notes.domain.usecases

import core.domain.model.Note
import feature_notes.domain.data_source.NotesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetAllNotesUseCase(
    private val notesDatasource: NotesDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Flow<List<Note>> =
        withContext(coroutineDispatcher) {
            notesDatasource.getAllNotes()
        }
}