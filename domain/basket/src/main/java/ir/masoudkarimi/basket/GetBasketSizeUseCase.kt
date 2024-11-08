package ir.masoudkarimi.basket

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBasketSizeUseCase @Inject constructor(
    private val basketRepository: BasketRepository
){

    operator fun invoke(): Flow<Int> {
        return basketRepository.getBasketSize()
    }

}