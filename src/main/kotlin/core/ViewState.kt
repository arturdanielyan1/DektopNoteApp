package core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import navigation.Argument

/**
 * Equivalent of ViewModel from Android
 * InputData is the argument type that will be passed to the screen
 * */
abstract class ViewState<InputData : Argument> {
    protected val viewStateScope = CoroutineScope(Dispatchers.Main)

    private val dataAssigned = false
    open fun putInitialData(data: InputData) {
        if(dataAssigned) throw IllegalStateException("Input Data can be assigned only once")
    }
    fun finalize() {
        viewStateScope.cancel()
    }
}