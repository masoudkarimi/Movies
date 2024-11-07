package ir.masoudkarimi.movies

import ir.masoudkarimi.model.Movie
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(): Result<List<Movie>> {
        return moviesRepository.getMovies(1)
    }
}
