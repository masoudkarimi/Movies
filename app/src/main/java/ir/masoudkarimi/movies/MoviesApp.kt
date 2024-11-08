package ir.masoudkarimi.movies

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.masoudkarimi.movie_detail.MovieDetailScreen
import ir.masoudkarimi.movies_list.MoviesListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "movies",
    ) {
        composable(
            route = "movies",
        ) {
            MoviesListScreen(
                onMovieClick = { movie ->
                    navController.navigate("movie/detail/${movie.id}")
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
            MovieDetailScreen()
        }
    }
}