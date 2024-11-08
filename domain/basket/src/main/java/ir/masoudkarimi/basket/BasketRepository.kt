package ir.masoudkarimi.basket

import ir.masoudkarimi.model.Product
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    suspend fun add(product: Product): Result<Unit>
    suspend fun remove(product: Product): Result<Unit>
    fun observeProduct(product: Product): Flow<Boolean>
    fun getBasketSize(): Flow<Int>
}