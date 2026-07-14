package ir.masoudkarimi.movies.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
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