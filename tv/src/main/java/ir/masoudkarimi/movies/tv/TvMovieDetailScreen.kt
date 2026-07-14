package ir.masoudkarimi.movies.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Button
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import ir.masoudkarimi.model.Movie
import ir.masoudkarimi.movie_detail.MovieDetailViewModel

@Composable
fun TvMovieDetailScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp, vertical = 40.dp),
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            uiState.error != null -> {
                TvErrorState(
                    modifier = Modifier.align(Alignment.Center),
                    message = uiState.error ?: "Unknown error",
                    onRetryClick = viewModel::retryClicked,
                )
            }

            uiState.movie != null -> {
                TvMovieDetailContent(
                    movie = uiState.movie!!,
                    isAddedToBasket = uiState.isAddedToBasket,
                    onBackClick = onBackClick,
                    onAddToBasketClick = viewModel::addMovieToBasket,
                    onRemoveFromBasketClick = viewModel::removeMovieFromBasket,
                )
            }
        }
    }
}

@Composable
private fun TvMovieDetailContent(
    movie: Movie,
    isAddedToBasket: Boolean,
    onBackClick: () -> Unit,
    onAddToBasketClick: (Movie) -> Unit,
    onRemoveFromBasketClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(48.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.posterUrl)
                .crossfade(true)
                .build(),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(260.dp)
                .aspectRatio(2f / 3f),
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.displaySmall,
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    onClick = onBackClick,
                ) {
                    Text(text = "Back")
                }

                if (isAddedToBasket) {
                    Button(
                        onClick = {
                            onRemoveFromBasketClick(movie)
                        },
                    ) {
                        Text(text = "Remove from Basket")
                    }
                } else {
                    Button(
                        onClick = {
                            onAddToBasketClick(movie)
                        },
                    ) {
                        Text(text = "Add to Basket")
                    }
                }
            }
        }
    }
}