package ir.masoudkarimi.movies_list

import ir.masoudkarimi.basket.BasketRepository
import ir.masoudkarimi.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeBasketRepository: BasketRepository {
    private val products = MutableStateFlow(emptyList<Product>())
    private val basketSize = products.map { it.size }

    override suspend fun add(product: Product): Result<Unit> {
        if (product in products.value) {
            return Result.failure(IllegalStateException("Item already exists in the basket"))
        }

        products.update {
            it + product
        }

        return Result.success(Unit)
    }

    override suspend fun remove(product: Product): Result<Unit> {
        if (product !in products.value) {
            return Result.failure(IllegalArgumentException("Item does not exist in the basket"))
        }

        products.update {
            it - product
        }

        return Result.success(Unit)
    }

    override fun observeProduct(product: Product): Flow<Boolean> {
        return products
            .map { it.contains(product) }
    }

    override fun observeBasketContent(): Flow<List<Product>> {
        return products
    }

    override fun getBasketSize(): Flow<Int> {
        return basketSize
    }

    fun clear() {
        products.update { emptyList() }
    }
}