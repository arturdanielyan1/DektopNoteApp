package navigation

import androidx.compose.foundation.clickable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role

fun Modifier.navigationClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    transitionDuration: Int = 500,
    onClick: () -> Unit
): Modifier = composed {

    val navigationClickListener = remember { NavigationClickListener(onClick, transitionDuration) }

    this.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = navigationClickListener::onNavigationClick
    )
}

class NavigationClickListener(
    private val onClick: () -> Unit,
    private val screenTransitionDuration: Int
) {
    private var lastClick = -1L

    fun onNavigationClick() {
        if(System.currentTimeMillis() - lastClick > screenTransitionDuration || lastClick == -1L) {
            onClick()
            lastClick = System.currentTimeMillis()
        }
    }
}