package core

import core.di.databaseModule
import core.di.dispatcherModule

val coreModule = dispatcherModule + databaseModule