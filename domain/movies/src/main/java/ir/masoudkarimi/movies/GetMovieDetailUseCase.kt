package ir.masoudkarimi.movies

import ir.masoudkarimi.model.Movie
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(movieId: Int): Result<Movie> {
        return moviesRepository.getMovieDetails(movieId)
    }
}