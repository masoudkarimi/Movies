package ir.masoudkarimi.basket

import ir.masoudkarimi.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveItemInBasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) {

    suspend operator fun invoke(product: Product): Flow<Boolean> {
        return basketRepository.observeProduct(product)
    }
}