package navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable

interface Argument {
    object Empty : Argument
}

class NavGraphBuilder {

    val screens: MutableMap<String, @Composable (Argument?) -> Unit> = mutableMapOf()

    inline fun <reified T : Argument> composable(
        route: String,
        noinline content: @Composable (T) -> Unit
    ) {
        screens[route] = {
            content.invoke(it as? T ?: Argument.Empty as T) /** Why cast of Empty to T doesn't fail??? */
        }
    }

    fun composable (
        route: String,
        content: @Composable () -> Unit
    ) {
        composable<Argument>(
            route = route
        ) { _ ->
            content()
        }
    }
}

class NavHost(
    private val navController: NavController,
    startDestination: String,
    navGraphBuilder: NavGraphBuilder.() -> NavGraphBuilder
) {
    init {
        navController.navigate(startDestination, null)
    }
    private val navGraph = navGraphBuilder.invoke(NavGraphBuilder())

    @Composable
    fun render() {
        AnimatedContent(
            targetState = navController.currentScreen.value,
            transitionSpec = {
                slideInHorizontally (
                    animationSpec = tween(800)
                ){ -it } togetherWith
                slideOutHorizontally(
                    animationSpec = tween(800)
                ) { it }
            }
        ) { destination ->
            navGraph.screens[destination]?.invoke(navController.lastArgs)
        }
    }
}