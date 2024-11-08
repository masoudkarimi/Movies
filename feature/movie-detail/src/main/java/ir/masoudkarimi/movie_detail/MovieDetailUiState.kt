package ir.masoudkarimi.movie_detail

import androidx.compose.runtime.Stable
import ir.masoudkarimi.model.Movie

@Stable
data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val error: String? = null
)