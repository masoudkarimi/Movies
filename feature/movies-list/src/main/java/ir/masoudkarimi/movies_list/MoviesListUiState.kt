package ir.masoudkarimi.movies_list

import ir.masoudkarimi.model.Movie

data class MoviesListUiState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null,
    val isBasketEnabled: Boolean = false
)