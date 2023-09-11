package feature_edit_note.presentation

import org.koin.dsl.module

val presentationModule = module {
    single {
        EditNoteViewState(get())
    }
}