package navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.*

class NavController {
    private val screenChain: Stack<String> = Stack()
    var currentScreen: MutableState<String> = mutableStateOf("")
        private set

    var lastArgs: Argument? = null
        private set

    fun <T : Argument> navigate(destination: String, args: T?) {
        lastArgs = args
        screenChain.add(destination)
        currentScreen.value = destination
    }

    fun navigate(destination: String) {
        this.navigate(destination, null)
    }

    fun back() {
        try {
            screenChain.pop()
            currentScreen.value = screenChain.peek()
        } catch (e: NoSuchElementException) {
            throw IllegalStateException("Can't navigate back from the first screen in BackStack")
        }
    }
}