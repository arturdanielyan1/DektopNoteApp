package feature_edit_note.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun EditNoteScreen(
    viewState: EditNoteViewState
) {
    val note by viewState.noteState.collectAsState()
    var titleState by remember { mutableStateOf(note.title) }
    var contentState by remember { mutableStateOf(note.content) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Navigate back",
            modifier = Modifier.clickable {
                // back
                viewState.saveNote(note.copy(
                    title = titleState,
                    content = contentState
                ))
            }
        )
        Column {
            BasicTextField(
                textStyle = TextStyle(
                    fontSize = 36.sp
                ),
                value = titleState,
                onValueChange = {
                    if (it.length <= 30) titleState = it
                },
                maxLines = 2,
            )
            Divider(Modifier.fillMaxWidth())
        }
        BasicTextField(
            modifier = Modifier.fillMaxSize(),
            textStyle = TextStyle(
                fontSize = 12.sp
            ),
            value = contentState,
            onValueChange = {
                if (it.length <= 500) contentState = it
            }
        )
    }
}