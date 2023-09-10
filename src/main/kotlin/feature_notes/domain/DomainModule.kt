package feature_notes.domain

import feature_notes.domain.usecases.DeleteNoteUseCase
import feature_notes.domain.usecases.GetAllNotesUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        GetAllNotesUseCase(get(), get())
    }

    single {
        DeleteNoteUseCase(get(), get())
    }
}