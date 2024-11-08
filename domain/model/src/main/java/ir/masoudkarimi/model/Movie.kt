package ir.masoudkarimi.model

data class Movie(
    override val id: Int,
    override val title: String,
    val posterUrl: String
) : Product