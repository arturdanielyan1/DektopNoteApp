package core.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import core.domain.model.Note
import feature_edit_note.presentation.component.EditNoteComponent
import feature_notes.presentation.component.NotesComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked(toIndex: Int)

    sealed interface Child {
        class NotesChild(val component: NotesComponent) : Child
        class EditNoteChild(val component: EditNoteComponent) : Child
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()


    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.NotesList,
            handleBackButton = true,
            childFactory = ::child
        )

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when(config) {
            is Config.NotesList -> RootComponent.Child.NotesChild(notesComponent(componentContext))
            is Config.EditNote -> RootComponent.Child.EditNoteChild(editNoteComponent(componentContext))
        }

    private fun notesComponent(componentContext: ComponentContext): NotesComponent =
        NotesComponent(componentContext, ::navigateToEditNoteScreen)

    private fun editNoteComponent(componentContext: ComponentContext): EditNoteComponent =
        EditNoteComponent(componentContext, ::navigateBack)

    private fun navigateToEditNoteScreen(note: Note) {
        navigation.push(Config.EditNote(note))
    }

    private fun navigateBack() {
        navigation.pop()
    }

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(toIndex)
    }

    @Parcelize
    sealed interface Config : Parcelable {
        data object NotesList : Config
        data class EditNote(val note: Note) : Config
    }
}