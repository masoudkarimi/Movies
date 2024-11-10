package ir.masoudkarimi.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerialName("data")
    val data: List<MovieDto>
)

@Serializable
data class MovieDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("poster")
    val posterUrl: String,
    @SerialName("images")
    val images: List<String>
)