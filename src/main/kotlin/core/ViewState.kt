package core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

abstract class ViewState {
    protected val viewStateScope = CoroutineScope(Dispatchers.Main)

    fun finalize() {
        viewStateScope.cancel()
    }
}