package feature_notes.data

import feature_notes.domain.data_source.NotesDataSource
import org.koin.dsl.module

val dataModule = module {
    single<NotesDataSource> {
        NotesDataSourceImpl(get())
    }
}