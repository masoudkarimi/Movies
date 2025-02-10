package ir.masoudkarimi.movies

import arrow.core.Either
import ir.masoudkarimi.model.Movie

interface MoviesRepository {
    sealed interface Error {
        val message: String
        data class NetworkError(
            val statusCode: Int?,
            override val message: String
        ): Error

    }
    suspend fun getMovies(page: Int): Either<MoviesError, List<Movie>>
    suspend fun getMovieDetails(movieId: Int): Either<MoviesError, Movie>
}