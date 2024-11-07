package ir.masoudkarimi.movies_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.masoudkarimi.movies.GetMoviesListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val moviesList: GetMoviesListUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            moviesList()
                .onSuccess {
                    Log.d("MoviesListViewModel", it.joinToString())
                }
                .onFailure {
                    Log.e("MoviesListViewModel", it.message, it)
                }
        }
    }
}