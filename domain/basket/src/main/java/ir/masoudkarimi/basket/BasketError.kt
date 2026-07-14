package ir.masoudkarimi.basket

sealed class BasketError {
    data object ItemAlreadyExists: BasketError()
    data object ItemNotFound: BasketError()
}