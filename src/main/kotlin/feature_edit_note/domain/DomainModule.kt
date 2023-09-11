package feature_edit_note.domain

import feature_edit_note.domain.usecases.SaveNoteUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        SaveNoteUseCase(get(), get())
    }
}