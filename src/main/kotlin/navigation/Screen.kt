package navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable

data class Screen(
    val route: String,
    val content: @Composable (Argument?) -> Unit,
    val enterTransition: () -> EnterTransition,
    val exitTransition: () -> ExitTransition,
)