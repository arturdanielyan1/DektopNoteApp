package feature_edit_note.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navigation.NavController


@Composable
fun EditNoteScreen(
    viewState: EditNoteViewState,
    navController: NavController
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
                navController.back()
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