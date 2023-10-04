package feature_notes.presentation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import feature_notes.presentation.component.NotesComponent
import feature_notes.presentation.component.NotesEvent
import feature_notes.presentation.composables.NoteItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesScreen(
    component: NotesComponent
) {
    val notes = component.state.subscribeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add note",
            modifier = Modifier.clickable {
//                navController.navigate("edit_notes", Note.EMPTY)
            }
        )
        LazyVerticalStaggeredGrid(
            modifier = Modifier.fillMaxSize(),
            columns = StaggeredGridCells.FixedSize(250.dp)
        ) {
            items(
                items = notes.value.notes,
                key = { it.id ?: -1 }
            ) { note ->
                NoteItem(
                    modifier = Modifier
                        .animateItemPlacement(tween(500))
                        .padding(8.dp),
                    note = note,
                    onDeleteNote = {
                        component.sendEvent(NotesEvent.DeleteNote(it))
                    },
                    onNoteClick = {
                        component.sendEvent(NotesEvent.GoToNote(it))
//                        navController.navigate("edit_notes", note)
                    }
                )
            }
        }
    }
}