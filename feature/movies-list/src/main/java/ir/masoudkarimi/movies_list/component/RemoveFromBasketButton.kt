package ir.masoudkarimi.movies_list.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RemoveFromBasketButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = MaterialTheme.colorScheme.error
        ),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text("Remove from Basket")
    }
}