package ir.masoudkarimi.movies.resource

import ir.masoudkarimi.model.Movie
import ir.masoudkarimi.network.MoviesService
import javax.inject.Inject

class MoviesRemoteSourceImpl @Inject constructor(
    private val moviesService: MoviesService,
    private val movieMapper: MovieMapper
) : MoviesRemoteResource {

    // TODO cancellation exception
    // TODO Error handling
    override suspend fun getMovies(page: Int): Result<List<Movie>> = runCatching {
        moviesService.getMovies(page).data
    }.map { movies ->
        movies.map(movieMapper)
    }

    // TODO cancellation exception
    // TODO Error handling
    override suspend fun getMovieDetails(movieId: Int): Result<Movie> = runCatching {
        moviesService.getMovieDetails(movieId)
    }.map(movieMapper)
}