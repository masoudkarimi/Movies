package ir.masoudkarimi.movies.resource

import arrow.core.Either
import ir.masoudkarimi.model.Movie
import ir.masoudkarimi.movies.MoviesError
import ir.masoudkarimi.network.MoviesService
import javax.inject.Inject

class MoviesRemoteSourceImpl @Inject constructor(
    private val moviesService: MoviesService,
    private val movieMapper: MovieMapper
) : MoviesRemoteResource {

    override suspend fun getMovies(page: Int): Either<MoviesError, List<Movie>> =
        Either.catch { moviesService.getMovies(page) }
            .mapLeft { MoviesError() }
            .map { response ->
                response.data
                    .map(movieMapper)
            }

    override suspend fun getMovieDetails(movieId: Int): Either<MoviesError, Movie> =
        Either.catch { moviesService.getMovieDetails(movieId) }
            .mapLeft { MoviesError() }
            .map(movieMapper)
}