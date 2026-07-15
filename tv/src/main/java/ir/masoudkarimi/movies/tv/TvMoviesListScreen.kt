package ir.masoudkarimi.movies.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ClassicCard
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import ir.masoudkarimi.model.Movie
import ir.masoudkarimi.movies_list.MovieState
import ir.masoudkarimi.movies_list.MoviesListViewModel
import ir.masoudkarimi.movies.tv.ui.theme.tvMoviesColors

@Composable
fun TvMoviesListScreen(
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MoviesListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp, vertical = 40.dp)
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            uiState.error != null -> {
                TvErrorState(
                    modifier = Modifier.align(Alignment.Center),
                    message = uiState.error ?: "Unknown Error",
                    onRetryClick = viewModel::retryClicked
                )
            }

            else -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(28.dp)
                ) {
                    Text(
                        text = "Movies",
                        style = MaterialTheme.typography.headlineLarge
                    )

                    TvMoviesGrid(
                        movies = uiState.movies,
                        onMovieClick = onMovieClick
                    )
                }
            }
        }
    }
}

@Composable
private fun TvMoviesGrid(
    movies: List<MovieState>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(bottom = 48.dp, top = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        items(
            items = movies,
            key = { movieState -> movieState.movie.id }
        ) { movieState ->
            TvMoviePosterCard(
                movieState = movieState,
                onMovieClick = onMovieClick
            )
        }
    }
}

@Composable
private fun TvMoviePosterCard(
    movieState: MovieState,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    val tvColors = MaterialTheme.tvMoviesColors

    ClassicCard(
        modifier = modifier.fillMaxWidth(),
        onClick = { onMovieClick(movieState.movie) },
        image = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movieState.movie.posterUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = movieState.movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            )
        },
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(12.dp),
                text = movieState.movie.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2
            )
        },
        colors = CardDefaults.colors(
            containerColor = tvColors.cardContainer,
            contentColor = tvColors.cardContent,
            focusedContainerColor = tvColors.cardFocusedContainer,
            focusedContentColor = tvColors.cardContent,
            pressedContainerColor = tvColors.cardFocusedContainer,
            pressedContentColor = tvColors.cardContent
        )
    )
}
