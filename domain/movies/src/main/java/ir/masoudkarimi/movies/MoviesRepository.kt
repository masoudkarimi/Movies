package ir.masoudkarimi.movies

import ir.masoudkarimi.model.Movie

interface MoviesRepository {
    suspend fun getMovies(page: Int): Result<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): Result<Movie>
}