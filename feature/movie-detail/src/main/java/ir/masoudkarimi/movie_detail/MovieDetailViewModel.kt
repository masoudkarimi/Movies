package ir.masoudkarimi.movie_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId: Int = savedStateHandle.get<String>("movieId")?.toInt()
        ?: throw IllegalStateException("movieId is required")

    init {
        Log.d("MovieDetail", "Movie ID= $movieId")
    }
}