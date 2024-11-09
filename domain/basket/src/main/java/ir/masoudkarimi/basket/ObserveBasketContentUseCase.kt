package ir.masoudkarimi.basket

import ir.masoudkarimi.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveBasketContentUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) {

    operator fun invoke(): Flow<List<Product>> {
        return basketRepository.observeBasketContent()
    }
}