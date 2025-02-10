package ir.masoudkarimi.movies.resource

import arrow.core.Either
import ir.masoudkarimi.model.Movie
import ir.masoudkarimi.movies.MoviesError

interface MoviesRemoteResource {
    suspend fun getMovies(page: Int): Either<MoviesError, List<Movie>>
    suspend fun getMovieDetails(movieId: Int): Either<MoviesError, Movie>
}