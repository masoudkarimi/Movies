package ir.masoudkarimi.movies

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ContentProvider(content: @Composable () -> Unit) {
    val viewModel: DrawerViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    textAlign = TextAlign.Center,
                    text = "Developer Options",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                HorizontalDivider()

                // Feature Flag Switch
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(
                        "Add To Basket Flag",
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = uiState.isBasketEnabled,
                        onCheckedChange = { isChecked ->
                            viewModel.setAddToBasketFlag(isChecked)
                        }
                    )
                }
            }
        },
        content = content
    )
}