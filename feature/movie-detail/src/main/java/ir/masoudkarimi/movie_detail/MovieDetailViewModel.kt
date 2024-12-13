package ir.masoudkarimi.movie_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.masoudkarimi.basket.AddItemToBasketUseCase
import ir.masoudkarimi.basket.ObserveItemInBasketUseCase
import ir.masoudkarimi.basket.RemoveFromBasketUseCase
import ir.masoudkarimi.core.android.executeUseCase
import ir.masoudkarimi.model.Movie
import ir.masoudkarimi.movies.GetMovieDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetail: GetMovieDetailUseCase,
    private val addToBasket: AddItemToBasketUseCase,
    private val removeFromBasket: RemoveFromBasketUseCase,
    private val observeItemInBasket: ObserveItemInBasketUseCase
) : ViewModel() {

    private val movieId = savedStateHandle.get<String>("movieId")?.toIntOrNull()
    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState
        .onStart {
            loadMovie()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieDetailUiState()
        )

    private fun loadMovie() {
        if (movieId != null) {
            fetchMovieDetails(movieId)
        } else {
            setErrorState(ERROR_MISSING_MOVIE_ID)
        }
    }

    private fun setLoadingState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun setErrorState(message: String) {
        _uiState.update { it.copy(isLoading = false, error = message) }
    }

    private fun setMovieDetails(movie: Movie) {
        _uiState.update { it.copy(isLoading = false, movie = movie, error = null) }
    }

    private fun fetchMovieDetails(movieId: Int) {
        setLoadingState(true)
        executeUseCase(
            useCase = { getMovieDetail(movieId) },
            onSuccess = { movie ->
                setMovieDetails(movie)
                checkItemInBasket(movie)
            },
            onFailure = {
                setErrorState(ERROR_LOADING_DETAILS)
            }
        )
    }

    fun addMovieToBasket(movie: Movie) {
        executeUseCase(
            useCase = { addToBasket(movie) },
            onSuccess = {
                Log.d("MovieDetailViewModel", "Movie added to basket")
            },
            onFailure = {
                Log.e("MovieDetailViewModel", "Failed to add movie to basket", it)
            }
        )
    }

    fun removeMovieFromBasket(movie: Movie) {
        executeUseCase(
            useCase = { removeFromBasket(movie) },
            onSuccess = {
                Log.d("MovieDetailViewModel", "Movie removed to basket")
            },
            onFailure = {
                Log.e("MovieDetailViewModel", "Failed to remove movie from basket", it)
            }
        )
    }

    private fun checkItemInBasket(movie: Movie) {

        viewModelScope.launch {
            observeItemInBasket(movie)
                .collect { isAddedToBasket ->
                    _uiState.update {
                        it.copy(
                            isAddedToBasket = isAddedToBasket
                        )
                    }
                }
        }
    }

    fun retryClicked() {
        loadMovie()
    }

    companion object {
        private const val ERROR_LOADING_DETAILS = "Failed to load movie details"
        private const val ERROR_MISSING_MOVIE_ID = "Missing movie ID"
    }
}