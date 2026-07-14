package ir.masoudkarimi.movies.tv

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.tv.material3.MaterialTheme

@Composable
fun TvMoviesApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val selectedDestination = TvDestination.selectDestinationForRoute(currentRoute)

    MaterialTheme {
        TvNavigationDrawer(
            modifier = modifier.fillMaxSize(),
            selectedDestination = selectedDestination,
            onDestinationClick = { destination ->
                navController.navigateToTopLevelDestination(destination)
            }
        ) {
            NavHost(
                modifier = modifier,
                navController = navController,
                startDestination = TvDestination.Movies.route
            ) {
                composable(route = TvDestination.Movies.route) {
                    TvMoviesListScreen(
                        onMovieClick = { movie ->
                            navController.navigate(
                                TvDestination.movieDetailRoute(movie.id)
                            )
                        }
                    )
                }

                composable(
                    route = TvDestination.MOVIE_DETAIL_ROUTE,
                    arguments = listOf(
                        navArgument("movieId") {
                            type = NavType.StringType
                        }
                    )
                ) {
                    TvMovieDetailScreen(
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }

                composable(route = TvDestination.Basket.route) {
                    TvPlaceholderScreen(title = "Basket")
                }

                composable(route = TvDestination.Settings.route) {
                    TvPlaceholderScreen(title = "Settings")
                }
            }
        }

    }
}

private fun NavHostController.navigateToTopLevelDestination(
    destination: TvDestination
) {
    navigate(destination.route) {
        popUpTo(TvDestination.Movies.route) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}