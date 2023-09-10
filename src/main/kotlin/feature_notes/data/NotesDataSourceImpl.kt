package feature_notes.data

import app.cash.sqldelight.coroutines.asFlow
import com.example.notedesktop.NoteDatabase
import core.domain.model.Note
import core.domain.mapper.toNote
import feature_notes.domain.data_source.NotesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesDataSourceImpl(
    db: NoteDatabase
) : NotesDataSource {

    private val queries = db.noteQueries

    override suspend fun getAllNotes(): Flow<List<Note>> {
        return queries.getAllNotes().asFlow().map {
            it.executeAsList().map { it.toNote() }
        }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}