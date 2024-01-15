package part5.jjs.domain.usecase

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.BasketProduct
import part5.jjs.domain.model.Product
import part5.jjs.domain.repository.BasketRepository
import javax.inject.Inject

class BasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) {
    fun getBasketProducts(): Flow<List<BasketProduct>> {
        return basketRepository.getBasketProducts()
    }

    suspend fun removeBasketProducts(product: Product) {
        basketRepository.removeBasketProduct(product = product)
    }

    suspend fun checkoutBasket(products: List<BasketProduct>) {
        basketRepository.checkoutBasket(products)
    }
}