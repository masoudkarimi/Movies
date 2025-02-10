package ir.masoudkarimi.basket

import arrow.core.Either
import ir.masoudkarimi.model.Product
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    suspend fun add(product: Product): Either<BasketError, Unit>
    suspend fun remove(product: Product): Either<BasketError, Unit>
    fun observeProduct(product: Product): Flow<Boolean>
    fun observeBasketContent(): Flow<List<Product>>
    fun getBasketSize(): Flow<Int>
}