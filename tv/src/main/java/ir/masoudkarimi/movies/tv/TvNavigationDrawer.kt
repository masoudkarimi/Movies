package ir.masoudkarimi.movies.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerItemDefaults
import androidx.tv.material3.Text

@Composable
fun TvNavigationDrawer(
    selectedDestination: TvDestination,
    onDestinationClick: (TvDestination) -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    NavigationDrawer(
        modifier = modifier,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TvDestination.topLevelDestinations.forEach { destination ->
                    NavigationDrawerItem(
                        selected = selectedDestination.route == destination.route,
                        onClick = { onDestinationClick(destination) },
                        colors = NavigationDrawerItemDefaults.colors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White,
                            inactiveContentColor = Color.White.copy(alpha = 0.55f),
                            focusedContainerColor = Color(0xFF3A3A3A),
                            focusedContentColor = Color.White,
                            selectedContainerColor = Color(0xFFE8E3F0),
                            selectedContentColor = Color(0xFF211B2D),
                            focusedSelectedContainerColor = Color(0xFFE8E3F0),
                            focusedSelectedContentColor = Color(0xFF211B2D),
                            pressedSelectedContainerColor = Color(0xFFE8E3F0),
                            pressedSelectedContentColor = Color(0xFF211B2D)
                        ),
                        leadingContent = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = null
                            )
                        }
                    ) {
                        Text(text = destination.label)
                    }
                }
            }
        },
        content = content
    )
}
