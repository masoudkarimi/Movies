package ir.masoudkarimi.basket

import arrow.core.raise.either
import arrow.core.raise.ensure
import ir.masoudkarimi.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor() : BasketRepository {
    private val products = MutableStateFlow(emptyList<Product>())
    private val basketSize = products.map { it.size }

    override suspend fun add(product: Product) = either {
        ensure(product !in products.value) { BasketError.ItemAlreadyExists }
        products.update {
            it + product
        }
    }

    override suspend fun remove(product: Product) = either {
        ensure(product in products.value) { BasketError.ItemNotFound }
        products.update {
            it - product
        }
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

}