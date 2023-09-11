import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import core.coreModule
import core.domain.model.Note
import feature_edit_note.featureEditNoteModule
import feature_edit_note.presentation.EditNoteScreen
import feature_edit_note.presentation.EditNoteViewState
import feature_notes.featureNotesModule
import feature_notes.presentation.NotesScreen
import feature_notes.presentation.NotesViewState
import navigation.NavHost
import navigation.rememberNavController
import org.koin.compose.rememberKoinInject
import org.koin.core.context.startKoin

@Composable
@Preview
fun App() {

    val navController = rememberNavController()

    NavHost(
        startDestination = "notes_screen",
        navController = navController
    ) {
        composable(
            route = "notes_screen"
        ) {
            val viewState = rememberKoinInject<NotesViewState>()
            NotesScreen(viewState, navController)
        }

        composable<Note>(
            route = "edit_notes"
        ) { note ->
            val viewState = rememberKoinInject<EditNoteViewState>()
            viewState.putInitialData(note)
            EditNoteScreen(viewState, navController)
        }
    }
}

fun main() {

    startKoin {
        modules(coreModule + featureNotesModule + featureEditNoteModule)
    }

    application {

        Window(
            title = "Note App",
            state = rememberWindowState(
                width = 500.dp,
                height = 500.dp,
            ),
            resizable = true,
            onCloseRequest = ::exitApplication
        ) {
            App()
        }
    }
}
