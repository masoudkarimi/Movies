package ir.masoudkarimi.movie_detail


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import ir.masoudkarimi.designsystem.DestructiveButton
import ir.masoudkarimi.designsystem.ErrorState
import ir.masoudkarimi.designsystem.PrimaryButton
import ir.masoudkarimi.model.Movie


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Movie Details")
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    bottom = paddingValues.calculateBottomPadding() + 16.dp
                ),
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .wrapContentHeight()
                            .align(Alignment.Center)
                    )
                }

                uiState.error != null -> {
                    ErrorState(
                        modifier = Modifier
                            .wrapContentHeight()
                            .align(Alignment.Center),
                        onRetryClick = viewModel::retryClicked,
                        error = uiState.error ?: "Unknown error",
                    )
                }

                uiState.movie != null -> {
                    MovieCard(
                        movie = uiState.movie!!,
                        isAddedToBasket = uiState.isAddedToBasket,
                        onAddToBasketClick = viewModel::addMovieToBasket,
                        onRemoveFromBasketClick = viewModel::removeMovieFromBasket
                    )
                }
            }
        }
    }
}


@Composable
fun MovieCard(
    movie: Movie,
    isAddedToBasket: Boolean,
    modifier: Modifier = Modifier,
    onAddToBasketClick: (Movie) -> Unit,
    onRemoveFromBasketClick: (Movie) -> Unit,
) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium
                )

                if (isAddedToBasket) {
                    DestructiveButton(
                        onClick = { onRemoveFromBasketClick(movie) }
                    ) {
                        Text(text = "Remove From Basket")
                    }
                } else {
                    PrimaryButton(
                        onClick = { onAddToBasketClick(movie) }
                    ) {
                        Text(text = "Add To Basket")
                    }
                }
            }

        }
    }
}