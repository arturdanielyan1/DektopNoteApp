import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import core.component.DefaultRootComponent
import core.component.RootComponent
import core.coreModule
import feature_edit_note.featureEditNoteModule
import feature_edit_note.presentation.EditNoteScreen
import feature_notes.featureNotesModule
import feature_notes.presentation.NotesScreen
import org.koin.core.Koin
import org.koin.core.context.startKoin

lateinit var koin: Koin

fun main() {

    koin = startKoin {
        modules(coreModule + featureNotesModule + featureEditNoteModule)
    }.koin

    val lifecycle = LifecycleRegistry()

    val root = DefaultRootComponent(DefaultComponentContext(lifecycle))

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
            MaterialTheme {
                Surface {
                    Children(
                        stack = root.stack,
                        modifier = Modifier.fillMaxSize(),
                        animation = stackAnimation(slide())
                    ) {
                        when(val instance = it.instance) {
                            is RootComponent.Child.NotesChild -> NotesScreen(instance.component)
                            is RootComponent.Child.EditNoteChild -> {
                                val note = (it.configuration as DefaultRootComponent.Config.EditNote).note
                                instance.component.putInitialData(note)
                                EditNoteScreen(instance.component)
                            }
                        }
                    }
                }
            }
        }
    }
}
