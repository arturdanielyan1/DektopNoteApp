package feature_edit_note.data

import feature_edit_note.domain.data_source.EditNoteDataSource
import org.koin.dsl.module


val dataModule = module {
    single<EditNoteDataSource> {
        EditNoteDataSourceImpl(get())
    }
}