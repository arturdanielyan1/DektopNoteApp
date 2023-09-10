package core.di

import com.example.notedesktop.NoteDatabase
import core.createDriver
import org.koin.dsl.module

val databaseModule = module {
    single {
        NoteDatabase.invoke(createDriver())
    }
}