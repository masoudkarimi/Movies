package ir.masoudkarimi.movies_list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MoviesListScreen(
    modifier: Modifier = Modifier,
    onMovieClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        Text(text = "Movies Screen")
        Button(onClick = onMovieClick) {
            Text(text = "Go to Movie Detail")
        }
    }
}