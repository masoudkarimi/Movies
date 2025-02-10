package ir.masoudkarimi.movies

import arrow.core.Either
import ir.masoudkarimi.model.Movie
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(movieId: Int): Either<MoviesError, Movie> =
        moviesRepository.getMovieDetails(movieId)
}