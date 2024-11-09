package ir.masoudkarimi.movies_list

import ir.masoudkarimi.model.Movie

data class MoviesListUiState(
    val isLoading: Boolean = false,
    val movies: List<MovieState> = emptyList(),
    val error: String? = null,
    val isBasketEnabled: Boolean = false,
    val basketSize: Int = 0
)

data class MovieState(
    val movie: Movie,
    val isAddedToBasket: Boolean
)