package feature_edit_note.data

import com.example.notedesktop.NoteDatabase
import core.domain.model.Note
import feature_edit_note.domain.data_source.EditNoteDataSource

class EditNoteDataSourceImpl(
    db: NoteDatabase
) : EditNoteDataSource {

    private val queries = db.noteQueries
    override suspend fun saveNote(note: Note) {
        queries.insertNote(note.id, note.title, note.content, note.created.timeInMillis)
    }
}