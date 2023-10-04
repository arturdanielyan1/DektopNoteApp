package core.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import java.util.Calendar

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val created: Calendar
) : Parcelable