package ir.masoudkarimi.movies

import arrow.core.Either
import ir.masoudkarimi.model.Movie

interface MoviesRepository {
    suspend fun getMovies(page: Int): Either<MoviesError, List<Movie>>
    suspend fun getMovieDetails(movieId: Int): Either<MoviesError, Movie>
}