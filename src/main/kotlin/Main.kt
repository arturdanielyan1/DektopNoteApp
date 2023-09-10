import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import core.coreModule
import core.domain.model.Note
import feature_notes.featureNotesModule
import feature_notes.presentation.NotesScreen
import feature_notes.presentation.NotesViewState
import navigation.NavController
import navigation.NavHost
import org.koin.compose.rememberKoinInject
import org.koin.core.context.startKoin

@Composable
@Preview
fun App() {

    val navController = remember { NavController() }

    NavHost(
        startDestination = "notes_screen",
        navController = navController
    ) {
        composable(
            route = "notes_screen"
        ) {
            val notesState = rememberKoinInject<NotesViewState>()
            NotesScreen(notesState, navController)
        }

        composable<Note>(
            route = "edit_notes"
        ) { note ->
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { navController.back() },
                text = note.title,
                textAlign = TextAlign.Center
            )
        }

        this
    }.render()

//    val notesState = rememberKoinInject<NotesViewState>()

//    NotesScreen(notesState)
}

fun main() {

    startKoin {
        modules(coreModule + featureNotesModule)
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
