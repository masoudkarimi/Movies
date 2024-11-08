package ir.masoudkarimi.basket

import ir.masoudkarimi.model.Product
import javax.inject.Inject

class RemoveFromBasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) {

    suspend operator fun invoke(product: Product): Result<Unit> {
        return basketRepository.remove(product)
    }
}