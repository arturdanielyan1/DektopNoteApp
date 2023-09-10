package feature_notes.domain.data_source

import core.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesDataSource {
    suspend fun getAllNotes(): Flow<List<Note>>
    suspend fun deleteNoteById(id: Long)
}