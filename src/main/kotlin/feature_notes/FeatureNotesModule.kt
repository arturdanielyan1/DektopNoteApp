package feature_notes

import feature_notes.data.dataModule
import feature_notes.domain.domainModule
import feature_notes.presentation.presentationModule

val featureNotesModule = dataModule + domainModule + presentationModule