package ir.masoudkarimi.movies.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerItemDefaults
import androidx.tv.material3.Text
import ir.masoudkarimi.movies.tv.ui.theme.tvMoviesColors

@Composable
fun TvNavigationDrawer(
    selectedDestination: TvDestination,
    onDestinationClick: (TvDestination) -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    val tvColors = MaterialTheme.tvMoviesColors

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
                            containerColor = tvColors.drawerItemContainer,
                            contentColor = tvColors.drawerItemContent,
                            inactiveContentColor = tvColors.drawerItemInactiveContent,
                            focusedContainerColor = tvColors.drawerItemFocusedContainer,
                            focusedContentColor = tvColors.drawerItemFocusedContent,
                            selectedContainerColor = tvColors.drawerItemSelectedContainer,
                            selectedContentColor = tvColors.drawerItemSelectedContent,
                            focusedSelectedContainerColor = tvColors.drawerItemSelectedContainer,
                            focusedSelectedContentColor = tvColors.drawerItemSelectedContent,
                            pressedSelectedContainerColor = tvColors.drawerItemSelectedContainer,
                            pressedSelectedContentColor = tvColors.drawerItemSelectedContent
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
