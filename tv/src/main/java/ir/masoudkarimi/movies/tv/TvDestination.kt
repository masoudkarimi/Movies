package ir.masoudkarimi.movies.tv

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TvDestination(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Movies : TvDestination("movies", "Movies", Icons.Filled.Home)

    data object Basket : TvDestination("basket", "Basket", Icons.Filled.ShoppingCart)

    data object Settings: TvDestination("settings", "Settings", Icons.Filled.Info)

    companion object {
        val topLevelDestinations= listOf(
            Movies,
            Basket,
            Settings
        )

        const val MOVIE_DETAIL_ROUTE = "movie/detail/{movieId}"

        fun movieDetailRoute(movieId: Int) = "movie/detail/$movieId"

        fun selectDestinationForRoute(route: String?): TvDestination {
            return when  {
                route == Basket.route -> Basket
                route == Settings.route -> Settings
                route?.startsWith("movie/detail") == true -> Movies
                else -> Movies
            }
        }
    }
}