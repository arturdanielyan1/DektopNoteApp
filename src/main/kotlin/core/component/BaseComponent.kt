package core.component

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseComponent<State : UiState, Event: UiEvent, Effect: UiEffect> {

    private val initialState: State by lazy { defineInitialState() }
    protected abstract fun defineInitialState(): State

    private val _state = MutableValue(initialState)
    val state: Value<State> = _state

    private val _event = MutableSharedFlow<Event>()

    private val _effect = MutableSharedFlow<Effect>()
    val effect = _effect.asSharedFlow()

    protected val currentState: State
        get() = state.value

    protected val scope = CoroutineScope(Dispatchers.Main)

    init {
        subscribeEvents()
    }

    fun sendEvent(event: Event) {
        scope.launch {
            _event.emit(event)
        }
    }

    private fun subscribeEvents() {
        scope.launch {
            _event.collect {
                handleEvent(it)
            }
        }
    }
    protected abstract fun handleEvent(event: Event)

    protected fun sendEffect(builder: () -> Effect) {
        scope.launch {
            _effect.emit(builder())
        }
    }

    protected fun changeState(modify: State.() -> State) {
        scope.launch {
            _state.update(modify)
        }
    }

    fun finalize() {
        scope.cancel()
    }
}