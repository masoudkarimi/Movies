package ir.masoudkarimi.model

/**
 * Represents a general product that can be added to the basket.
 * This interface is designed to provide a common structure for various types of products
 * (e.g., movies, books, etc.), making it easier to manage them uniformly in the basket.
 */

interface Product {
    val id: Int
    val title: String
}
