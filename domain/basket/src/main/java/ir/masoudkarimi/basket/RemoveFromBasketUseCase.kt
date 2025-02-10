package ir.masoudkarimi.basket

import arrow.core.Either
import ir.masoudkarimi.model.Product
import javax.inject.Inject

class RemoveFromBasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) {
    suspend operator fun invoke(product: Product): Either<BasketError, Unit> {
        return basketRepository.remove(product)
    }
}