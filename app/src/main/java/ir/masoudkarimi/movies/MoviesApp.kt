package ir.masoudkarimi.movies

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ir.masoudkarimi.movies.ui.MoviesList
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
            startDestination = MoviesList,
        ) {
            composable<MoviesList> {
                MoviesListScreen()
            }
        }
    }
}