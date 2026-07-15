package ir.masoudkarimi.movies.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.masoudkarimi.movies.tv.ui.theme.TvMoviesTheme

@AndroidEntryPoint
class TvMainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TvMoviesTheme {
                TvMoviesApp(
                    navController = rememberNavController()
                )
            }
        }
    }
}
