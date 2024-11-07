package ir.masoudkarimi.network

import ir.masoudkarimi.network.model.MovieDto
import ir.masoudkarimi.network.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("movies")
    suspend fun getMovies(@Query("page") page: Int): MoviesResponse

    @GET("movies/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: Int): MovieDto
}