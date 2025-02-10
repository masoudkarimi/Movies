package ir.masoudkarimi.movies

import arrow.core.Either
import ir.masoudkarimi.model.Movie
import ir.masoudkarimi.movies.resource.MoviesRemoteResource
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteResource: MoviesRemoteResource
) : MoviesRepository {
    override suspend fun getMovies(page: Int): Either<MoviesError, List<Movie>> =
        moviesRemoteResource.getMovies(page)

    override suspend fun getMovieDetails(movieId: Int): Either<MoviesError, Movie> =
        moviesRemoteResource.getMovieDetails(movieId)
}