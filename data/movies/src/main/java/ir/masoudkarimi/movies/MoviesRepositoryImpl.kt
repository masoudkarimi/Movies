package ir.masoudkarimi.movies

import ir.masoudkarimi.model.Movie
import ir.masoudkarimi.movies.resource.MoviesRemoteResource
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteResource: MoviesRemoteResource
): MoviesRepository {
    override suspend fun getMovies(page: Int): Result<List<Movie>> {
        return moviesRemoteResource.getMovies(page)
    }

    override suspend fun getMovieDetails(movieId: Int): Result<Movie> {
        return moviesRemoteResource.getMovieDetails(movieId)
    }
}