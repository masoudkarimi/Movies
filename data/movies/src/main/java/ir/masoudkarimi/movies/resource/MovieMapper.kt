package ir.masoudkarimi.movies.resource

import ir.masoudkarimi.model.Movie
import ir.masoudkarimi.network.model.MovieDto
import javax.inject.Inject

class MovieMapper @Inject constructor() : (MovieDto) -> Movie {
    override fun invoke(dto: MovieDto): Movie {
        return Movie(
            id = dto.id,
            title = dto.title,
            posterUrl = dto.images.firstOrNull() ?: dto.posterUrl
        )
    }
}