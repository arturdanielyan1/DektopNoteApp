package core.component

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.arkivanov.decompose.value.updateAndGet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseComponent<State : UiState, Event: UiEvent, Effect: UiEffect, InputData> {

    private val initialState: State by lazy { defineInitialState() }
    protected abstract fun defineInitialState(): State

    val state = MutableValue(initialState)
//    val state: Value<State> = _state

    private val _event = MutableSharedFlow<Event>()

    private val _effect = MutableSharedFlow<Effect>()
    val effect = _effect.asSharedFlow()

    protected val currentState: State
        get() = state.value

    protected val componentScope = CoroutineScope(Dispatchers.Main)


    private val dataAssigned = false
    open fun putInitialData(data: InputData) {
        if(dataAssigned) throw IllegalStateException("Input Data can be assigned only once")
    }
    init {
        subscribeEvents()
    }

    fun sendEvent(event: Event) {
        componentScope.launch {
            _event.emit(event)
        }
    }

    private fun subscribeEvents() {
        componentScope.launch {
            _event.collect {
                handleEvent(it)
            }
        }
    }
    protected abstract fun handleEvent(event: Event)

    protected fun sendEffect(builder: () -> Effect) {
        componentScope.launch {
            _effect.emit(builder())
        }
    }

    protected fun changeState(modify: State.() -> State) {
        componentScope.launch {
            val upd = state.updateAndGet {
                it.modify()
            }
            println(upd)
        }
    }

    fun finalize() {
        componentScope.cancel()
    }
}