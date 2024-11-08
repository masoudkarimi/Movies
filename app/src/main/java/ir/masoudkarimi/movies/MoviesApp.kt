package ir.masoudkarimi.movies

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import ir.masoudkarimi.movie_detail.MovieDetailScreen
import ir.masoudkarimi.movies_list.MoviesListScreen

@Composable
fun MoviesApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .consumeWindowInsets(paddingValues),
            navController = navController,
            startDestination = "movies",
        ) {
            composable(
                route = "movies",
            ) {
                MoviesListScreen(
                    modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
                    onMovieClick = { movie ->
                        navController.navigate("movie/detail/${movie.movieId}")
                    }
                )
            }

            composable(
                route = "movie/detail/{movieId}",
                arguments = listOf(
                    navArgument("movieId") {
                        type = NavType.StringType
                    }
                )
            ) {
                MovieDetailScreen(
                    modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
                )
            }
        }
    }
}