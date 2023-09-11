package feature_edit_note

import feature_edit_note.data.dataModule
import feature_edit_note.domain.domainModule
import feature_edit_note.presentation.presentationModule

val featureEditNoteModule = dataModule + domainModule + presentationModule