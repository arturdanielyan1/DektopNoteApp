package feature_notes.presentation

import org.koin.dsl.module

val presentationModule = module {
    single {
        NotesViewState(get(), get())
    }
}