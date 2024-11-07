package ir.masoudkarimi.movie_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Text(text = "Movies Detail Screen")
    }
}