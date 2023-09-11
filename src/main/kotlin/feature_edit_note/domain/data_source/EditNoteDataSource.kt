package feature_edit_note.domain.data_source

import core.domain.model.Note

interface EditNoteDataSource {
    suspend fun saveNote(note: Note)
}