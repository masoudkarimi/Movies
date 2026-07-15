package ir.masoudkarimi.movies.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import ir.masoudkarimi.movies.tv.ui.theme.tvButtonColors

@Composable
fun TvErrorState(
    message: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.titleLarge,
        )

        Button(
            onClick = onRetryClick,
            colors = tvButtonColors(),
        ) {
            Text(text = "Retry")
        }
    }
}
