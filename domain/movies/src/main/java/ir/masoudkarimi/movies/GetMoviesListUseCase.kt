package ir.masoudkarimi.movies

import arrow.core.Either
import ir.masoudkarimi.model.Movie
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(): Either<MoviesError, List<Movie>> =
        moviesRepository.getMovies(1)
}
