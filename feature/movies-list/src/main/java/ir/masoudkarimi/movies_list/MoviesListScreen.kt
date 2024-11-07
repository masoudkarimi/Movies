package ir.masoudkarimi.movies_list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MoviesListScreen(
    modifier: Modifier = Modifier,
    onMovieClick: () -> Unit = {},
    viewModel: MoviesListViewModel = hiltViewModel()
) {

    Column(modifier = modifier) {
        Text(text = "Movies Screen")
        Button(onClick = onMovieClick) {
            Text(text = "Go to Movie Detail")
        }
    }
}