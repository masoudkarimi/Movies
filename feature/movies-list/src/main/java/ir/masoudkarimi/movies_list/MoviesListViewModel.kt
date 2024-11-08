package ir.masoudkarimi.movies_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.masoudkarimi.basket.GetBasketSizeUseCase
import ir.masoudkarimi.movies.GetMoviesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val moviesList: GetMoviesListUseCase,
    private val getBasketSize: GetBasketSizeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MoviesListUiState())
    val uiState: StateFlow<MoviesListUiState> = _uiState
        .onStart {
            loadMovies()
            observeBasket()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MoviesListUiState()
        )


    private fun loadMovies() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            moviesList()
                .onSuccess { movies ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            movies = it.movies + movies
                        )
                    }
                }
                .onFailure {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "Failed to load movies",
                            movies = emptyList()
                        )
                    }
                }
        }
    }

    private fun observeBasket() {
        viewModelScope.launch {
            getBasketSize()
                .collect { basketSize ->
                    _uiState.update { it.copy(basketSize = basketSize) }
                }
        }
    }

}