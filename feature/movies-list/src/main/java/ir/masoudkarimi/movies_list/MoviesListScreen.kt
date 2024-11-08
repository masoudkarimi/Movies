package ir.masoudkarimi.movies_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import ir.masoudkarimi.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesListScreen(
    modifier: Modifier = Modifier,
    onMovieClick: (Movie) -> Unit = {},
    viewModel: MoviesListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Movies")
                },
                actions = {
                    BasketIconWithBadge(basketItemCount = 13)
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                    Text(
                        text = uiState.error ?: "Unknown error",
                        color = Color.Red,
                        modifier = Modifier
                            .wrapContentHeight()
                            .align(Alignment.Center)
                    )
                }

                else -> {
                    MoviesList(
                        modifier = modifier,
                        movies = uiState.movies,
                        onMovieClick = onMovieClick
                    )
                }
            }
        }
    }
}

@Composable
fun MoviesList(
    modifier: Modifier,
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies, key = { it.movieId }) { movie ->
            MovieCard(movie, false, onMovieClick)
        }
    }
}

@Composable
fun MovieCard(movie: Movie, isBasketEnabled: Boolean, onMovieClick: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable { onMovieClick(movie) },
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
                    .height(140.dp)
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
                if (isBasketEnabled) {
                    Button(
                        onClick = { /* Handle Add to Basket/Remove from Basket */ },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Text("Add to Basket")
                    }
                }
            }

        }
    }
}

@Composable
fun BasketIconWithBadge(basketItemCount: Int) {
    Box(
        modifier = Modifier
            .padding(end = 16.dp)
            .size(24.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Basket Icon",
            modifier = Modifier.align(Alignment.Center)
        )

        // Show badge only if there are items in the basket
        if (basketItemCount > 0) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 8.dp, y = (-4).dp) // Adjust the position
                    .size(18.dp)
                    .background(Color.Red, shape = MaterialTheme.shapes.small)
                    .padding(2.dp)
            ) {
                Text(
                    text = "$basketItemCount",
                    color = Color.White,
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}