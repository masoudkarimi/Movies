package ir.masoudkarimi.network.resource

import ir.masoudkarimi.model.Movie

interface MoviesRemoteResource {
    suspend fun getMovies(page: Int): Result<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): Result<Movie>
}