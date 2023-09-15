package navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

interface Argument {
    object Empty : Argument
}

class NavGraphBuilder {

    val screens: MutableList<Screen> = mutableListOf()

    fun getScreenContent(route: String): @Composable (Argument?) -> Unit =
        screens.find { it.route == route }?.content ?: throw NoSuchElementException("Couldn't find the screen $route")

    fun getScreenTransitions(route: String): ContentTransform {
        return screens.find { it.route == route }?.run {
            enterTransition() togetherWith exitTransition()
        } ?: throw NoSuchElementException("Couldn't find the screen $route")
    }

    inline fun <reified T : Argument> composable(
        route: String,
        noinline enterTransition: () -> EnterTransition = { slideInHorizontally(tween(500)) { it } },
        noinline exitTransition: () -> ExitTransition = { slideOutHorizontally(tween(500)) { -it } },
        noinline content: @Composable (T) -> Unit
    ) {
        screens.add(
            Screen(
                route = route,
                content = {
                    content.invoke(it as? T ?: Argument.Empty as T) /** Why cast of Empty to T doesn't fail??? */
                },
                enterTransition = enterTransition,
                exitTransition = exitTransition
            )
        )
    }

    fun composable (
        route: String,
        enterTransition: () -> EnterTransition = { slideInHorizontally(tween(500)) { it } },
        exitTransition: () -> ExitTransition = { slideOutHorizontally(tween(500)) { -it } },
        content: @Composable () -> Unit
    ) {
        composable<Argument>(
            route = route,
            enterTransition = enterTransition,
            exitTransition = exitTransition
        ) { _ ->
            content()
        }
    }
}

class NavHostController(
    private val navController: NavController,
    startDestination: String,
    navGraphBuilder: NavGraphBuilder.() -> Unit
) {
    init {
        navController.navigate(startDestination, null)
    }
    private val navGraph = NavGraphBuilder().apply(navGraphBuilder)
    private var currentExitTransition: ExitTransition = slideOutHorizontally(tween(500)) { -it }

    @Composable
    fun render() {
        LaunchedEffect(navController.currentScreen.value) {
            currentExitTransition = navGraph.getScreenTransitions(navController.currentScreen.value).initialContentExit
        }
        AnimatedContent(
            targetState = navController.currentScreen.value,
            transitionSpec = {
                navGraph.getScreenTransitions(this.targetState).targetContentEnter togetherWith
                currentExitTransition
            }
        ) { destination ->
            navGraph.getScreenContent(destination).invoke(navController.lastArgs)
        }
    }
}

@Composable
fun NavHost(
    navController: NavController,
    startDestination: String,
    navGraphBuilder: NavGraphBuilder.() -> Unit
) {
    NavHostController(
        navController, startDestination, navGraphBuilder
    ).render()
}

@Composable
fun rememberNavController() = remember {
    NavController()
}