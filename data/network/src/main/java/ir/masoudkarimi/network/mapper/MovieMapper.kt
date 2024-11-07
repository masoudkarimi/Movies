package ir.masoudkarimi.network.mapper

import ir.masoudkarimi.model.Movie
import ir.masoudkarimi.network.model.MovieDto
import javax.inject.Inject

class MovieMapper @Inject constructor() : (MovieDto) -> Movie {
    override fun invoke(dto: MovieDto): Movie {
        return Movie(
            movieId = dto.id,
            title = dto.title,
            posterUrl = dto.posterUrl
        )
    }
}