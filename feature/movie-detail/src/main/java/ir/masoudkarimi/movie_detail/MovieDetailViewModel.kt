package ir.masoudkarimi.movie_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val movieId: Int? = savedStateHandle.get<String>("movieId")?.toInt()

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState
        .onStart {
            fetchMovieDetails(movieId)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieDetailUiState()
        )

    private fun fetchMovieDetails(movieId: Int?) {
        if (movieId == null) {
            _uiState.update {
                it.copy(isLoading = false, error = "Mossing movie ID")
            }
            return
        }

        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            getMovieDetailUseCase(movieId)
                .onSuccess { movie ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            movie = movie
                        )
                    }
                }
                .onFailure {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "Failed to load movie details"
                        )
                    }
                }
        }
    }
}