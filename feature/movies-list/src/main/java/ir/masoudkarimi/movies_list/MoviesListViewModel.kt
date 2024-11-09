package ir.masoudkarimi.movies_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.masoudkarimi.basket.AddItemToBasketUseCase
import ir.masoudkarimi.basket.ObserveBasketContentUseCase
import ir.masoudkarimi.basket.RemoveFromBasketUseCase
import ir.masoudkarimi.core.android.executeUseCase
import ir.masoudkarimi.feature_flag.FeatureFlag
import ir.masoudkarimi.feature_flag.FeatureFlagRepository
import ir.masoudkarimi.model.Movie
import ir.masoudkarimi.model.Product
import ir.masoudkarimi.movies.GetMoviesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val moviesList: GetMoviesListUseCase,
    private val addToBasket: AddItemToBasketUseCase,
    private val removeFromBasket: RemoveFromBasketUseCase,
    private val observeBasketContent: ObserveBasketContentUseCase,
    private val featureFlagRepository: FeatureFlagRepository
) : ViewModel() {

    private val addToCartFeatureFlag = flow {
        emit(featureFlagRepository.isEnabled(FeatureFlag.ADD_TO_BASKET_IN_LIST_SCREEN.key))
    }

    private val _uiState = MutableStateFlow(MoviesListUiState())
    val uiState: StateFlow<MoviesListUiState> = combine(
        _uiState,
        addToCartFeatureFlag,
        observeBasketContent()
    ) { state, featureFlagEnabled, basketContent ->
        state.copy(
            isBasketEnabled = featureFlagEnabled,
            basketSize = basketContent.size,
            movies = updateMoviesWithBasketContent(state.movies, featureFlagEnabled, basketContent)
        )
    }.onStart {
        loadMovies()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MoviesListUiState()
    )

    private fun updateMoviesWithBasketContent(
        movies: List<MovieState>,
        isBasketFeatureEnabled: Boolean,
        basketContent: List<Product>
    ): List<MovieState> {
        return movies.map { movie ->
            if (isBasketFeatureEnabled) {
                movie.copy(isAddedToBasket = basketContent.contains(movie.movie))
            } else {
                movie
            }
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun loadMovies() {
        updateLoadingState(true)
        executeUseCase(
            moviesList::invoke,
            onSuccess = { movies -> handleMoviesSuccess(movies) },
            onFailure = { handleMoviesFailure() }
        )
    }

    private fun handleMoviesSuccess(movies: List<Movie>) {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                error = null,
                movies = state.movies + movies.map { movie ->
                    MovieState(movie = movie, isAddedToBasket = false)
                }
            )
        }
    }

    private fun handleMoviesFailure() {
        _uiState.update {
            it.copy(isLoading = false, error = "Failed to load movies", movies = emptyList())
        }
    }

    fun addMovieToBasket(movie: Movie) {
        executeUseCase(
            useCase = { addToBasket(movie) },
            onSuccess = {
                Log.d("MoviesListViewModel", "Movie added to basket")
            },
            onFailure = {
                Log.e("MoviesListViewModel", "Failed to add movie to basket", it)
            }
        )
    }

    fun removeMovieFromBasket(movie: Movie) {
        executeUseCase(
            useCase = { removeFromBasket(movie) },
            onSuccess = {
                Log.d("MoviesListViewModel", "Movie removed to basket")
            },
            onFailure = {
                Log.e("MoviesListViewModel", "Failed to remove movie from basket", it)
            }
        )
    }

}