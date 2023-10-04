package feature_notes.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.domain.model.Note

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    onDeleteNote: (id: Long) -> Unit,
    onNoteClick: (note: Note) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(
                    size = 10.dp
                ),
            ).clip(RoundedCornerShape(10.dp))
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colors.onBackground
                ),
                shape = RoundedCornerShape(10.dp),
            ).clickable { onNoteClick(note) }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = note.title,
                fontSize = 36.sp,
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove note",
                modifier = Modifier.clickable { onDeleteNote(note.id ?: -1) }
            )
        }
        Text(
            text = note.content,
        )
    }
}