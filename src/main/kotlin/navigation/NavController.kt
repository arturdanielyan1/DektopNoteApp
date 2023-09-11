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
        currentScreen.value = destination
        lastArgs = args
        screenChain.add(destination)
        println(screenChain)
    }

    fun navigate(destination: String) {
        this.navigate(destination, null)
    }

    fun back() {
        try {
            screenChain.pop()
            println(screenChain)
            currentScreen.value = screenChain.peek()
        } catch (e: EmptyStackException) {
            throw IllegalStateException("Can't navigate back from the first screen in BackStack")
        }
    }
}